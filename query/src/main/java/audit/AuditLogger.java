package org.axonframework.samples.bank.query.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.messaging.MetaData;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
public class AuditLogger {

    private final AuditLogElasticsearchRepository elasticRepo;
    private final ObjectMapper objectMapper;

    public AuditLogger(AuditLogElasticsearchRepository elasticRepo) {
        this.elasticRepo = elasticRepo;
        this.objectMapper = new ObjectMapper();
    }

    @EventHandler
    public void on(Object event, @Timestamp Instant timestamp,
                   MetaData metaData) {
        try {
            String aggregateId = (String) metaData.get("aggregateIdentifier");

            AuditLog log = new AuditLog();
            log.setId(UUID.randomUUID().toString());
            log.setEventType(event.getClass().getSimpleName());
            log.setAggregateId(aggregateId);
            log.setPayload(objectMapper.writeValueAsString(event));
            log.setTimestamp(LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault()));

            // Kirim ke Logstash via TCP
            try (Socket socket = new Socket("logstash", 5000);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                out.println(objectMapper.writeValueAsString(log));
            } catch (Exception ex) {
                System.err.println("Gagal mengirim audit log ke Logstash: " + ex.getMessage());
            }

            elasticRepo.save(log);
        } catch (JsonProcessingException e) {
            System.err.println("Gagal mencatat audit log ke Elasticsearch: " + e.getMessage());
        }
    }

}
package org.axonframework.samples.bank.query.audit;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Document(indexName = "audit-log", createIndex = true)
public class AuditLog {

    @Id
    private String id;

    private String eventType;
    private String aggregateId;
    private String payload;
    private LocalDateTime timestamp;

    // Getter dan Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
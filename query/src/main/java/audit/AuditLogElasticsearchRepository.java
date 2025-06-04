package org.axonframework.samples.bank.query.audit;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.net.Socket;        // For the Socket class
import java.io.PrintWriter;   // For the PrintWriter class

public interface AuditLogElasticsearchRepository extends ElasticsearchRepository<AuditLog, String> {
}
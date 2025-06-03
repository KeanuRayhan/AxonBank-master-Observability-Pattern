package org.axonframework.samples.bank.query.audit;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuditLogElasticsearchRepository extends ElasticsearchRepository<AuditLog, String> {
}
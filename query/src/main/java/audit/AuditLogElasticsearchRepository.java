// package org.axonframework.samples.bank.query.audit;
package audit;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuditLogElasticsearchRepository extends ElasticsearchRepository<AuditLog, String> {
}
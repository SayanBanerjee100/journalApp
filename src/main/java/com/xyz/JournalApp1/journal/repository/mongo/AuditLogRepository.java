package com.xyz.JournalApp1.journal.repository.mongo;

import com.xyz.JournalApp1.journal.model.mongo.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
}

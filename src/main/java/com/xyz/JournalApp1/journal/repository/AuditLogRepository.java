package com.xyz.JournalApp1.journal.repository;

import com.xyz.JournalApp1.journal.model.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
}

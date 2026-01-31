package com.xyz.JournalApp1.journal.kafka.consumer;

import com.xyz.JournalApp1.journal.kafka.KafkaTopics;
import com.xyz.JournalApp1.journal.kafka.events.JournalCreatedEvent;
import com.xyz.JournalApp1.journal.model.mongo.AuditLog;
import com.xyz.JournalApp1.journal.repository.mongo.AuditLogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JournalEventConsumer {

    private final AuditLogRepository auditLogRepository;

    public JournalEventConsumer(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @KafkaListener(topics = KafkaTopics.JOURNAL_CREATED, groupId = "journal-group")
    public void handleJournalCreated(JournalCreatedEvent event) {

        AuditLog log = new AuditLog(
                "JOURNAL_CREATED",
                event.getJournalId(),
                event.getUserEmailId(),
                "Journal created with title: " + event.getTitle(),
                Instant.now()
        );

        auditLogRepository.save(log);
    }
}

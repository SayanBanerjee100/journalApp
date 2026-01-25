package com.xyz.JournalApp1.journal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "audit_logs")
public class AuditLog {

    @Id
    private String id;

    private String eventType;
    private String journalId;
    private String userEmailId;
    private String message;
    private Instant createdAt;

    public AuditLog() {}

    public AuditLog(String eventType, String journalId, String userEmailId, String message, Instant createdAt) {
        this.eventType = eventType;
        this.journalId = journalId;
        this.userEmailId = userEmailId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getJournalId() {
        return journalId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

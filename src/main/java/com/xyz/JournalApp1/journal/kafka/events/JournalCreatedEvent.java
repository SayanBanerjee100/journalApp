package com.xyz.JournalApp1.journal.kafka.events;

import java.time.Instant;

public class JournalCreatedEvent {

    private String journalId;
    private String userEmailId;
    private String title;
    private Instant createdAt;

    public JournalCreatedEvent() {}

    public JournalCreatedEvent(String journalId, String userEmailId, String title, Instant createdAt) {
        this.journalId = journalId;
        this.userEmailId = userEmailId;
        this.title = title;
        this.createdAt = createdAt;
    }

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

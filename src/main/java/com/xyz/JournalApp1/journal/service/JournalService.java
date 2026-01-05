package com.xyz.JournalApp1.journal.service;

import com.xyz.JournalApp1.journal.model.JournalEntry;
import com.xyz.JournalApp1.journal.repository.JournalRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public JournalEntry createEntry(JournalEntry entry) {
        return journalRepository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    public JournalEntry getEntryById(String id) {
        return journalRepository.findById(id).orElse(null);
    }

    public void deleteEntry(String id) {
        journalRepository.deleteById(id);
    }
    public List<JournalEntry> getEntriesByUserId(String userId) {
        return journalRepository.findByUserId(userId);
    }
}

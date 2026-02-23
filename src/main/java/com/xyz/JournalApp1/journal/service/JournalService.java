package com.xyz.JournalApp1.journal.service;


import com.xyz.JournalApp1.journal.model.mongo.JournalEntry;
import com.xyz.JournalApp1.journal.repository.mongo.JournalRepository;
import com.xyz.JournalApp1.journal.repository.mongo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    private final UserRepository userRepository;

    public JournalService(JournalRepository journalRepository,
                          UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }
    public JournalEntry createJournal(JournalEntry entry, String username) {

        entry.setUsername(username);
        entry.setEmailId(username);
        entry.setCreatedAt(Instant.now());
        entry.setUpdatedAt(Instant.now());

        JournalEntry saved = journalRepository.save(entry);



        return saved;

    }


    public List<JournalEntry> getUserJournals(String username) {
        return journalRepository.findByUsername(username);
    }
    public JournalEntry updateJournal(
            String journalId,
            String username,
            JournalEntry updatedJournal
    ) {
        JournalEntry existingJournal = journalRepository
                .findByIdAndUsername(journalId, username)
                .orElseThrow(() ->
                        new RuntimeException("Journal not found or not owned by user")
                );

        existingJournal.setTitle(updatedJournal.getTitle());
        existingJournal.setContent(updatedJournal.getContent());
        existingJournal.setUpdatedAt(Instant.now());


        return journalRepository.save(existingJournal);
    }
    public void deleteJournal(String id,String username)
    {

        JournalEntry journal = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found"));

        if (!journal.getUsername().equals(username)) {
            throw new RuntimeException("Not allowed");
        }

        journalRepository.deleteById(id);


    }

}

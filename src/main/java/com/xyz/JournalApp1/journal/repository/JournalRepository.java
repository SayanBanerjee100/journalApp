package com.xyz.JournalApp1.journal.repository;

import com.xyz.JournalApp1.journal.model.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalRepository extends MongoRepository<JournalEntry,String> {
    List<JournalEntry> findByUserId(String id);
}

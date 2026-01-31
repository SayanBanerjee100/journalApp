package com.xyz.JournalApp1.journal.repository.mongo;

import com.xyz.JournalApp1.journal.model.mongo.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;



import java.util.List;
import java.util.Optional;

public interface JournalRepository extends MongoRepository<JournalEntry,String> {
    List<JournalEntry> findByUsername(String name);


    Optional<JournalEntry> findByIdAndUsername(String id, String username);
}

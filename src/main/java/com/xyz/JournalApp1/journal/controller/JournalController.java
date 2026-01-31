package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.model.mongo.JournalEntry;
import com.xyz.JournalApp1.journal.service.JournalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService)
    {
        this.journalService = journalService;

    }

    @PostMapping
    public JournalEntry create(@RequestBody JournalEntry entry) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return journalService.createJournal(entry, username);
    }
    @PutMapping("/{id}")
    public JournalEntry updateJournal(
            @PathVariable String id,
            @RequestBody JournalEntry journal
    ) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return journalService.updateJournal(id, username, journal);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable String id)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        journalService.deleteJournal(id,username);
        return ResponseEntity.ok("Journal Deleted");
    }
    @GetMapping
    public List<JournalEntry> getUserJournals()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return journalService.getUserJournals(username);
    }

}

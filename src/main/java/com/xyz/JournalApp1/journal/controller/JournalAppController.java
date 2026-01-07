package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.model.JournalEntry;
import com.xyz.JournalApp1.journal.service.JournalService;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping("/journal")
public class JournalAppController {

    private final JournalService journalService;

    public JournalAppController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/create")
    public JournalEntry create(@RequestBody JournalEntry entry) {
        return journalService.createEntry(entry);
    }
    @PostMapping("/create/{userId}")
    public JournalEntry createEntry(
            @PathVariable String userId,
            @RequestBody JournalEntry entry) {

        entry.setId(userId);
        return journalService.createEntry(entry);
    }

    @GetMapping("/get")
    public List<JournalEntry> getAll() {
        return journalService.getAllEntries();
    }

    @GetMapping("/get/{id}")
    public JournalEntry getById(@PathVariable String id) {
        return journalService.getEntryById(id);
    }
}

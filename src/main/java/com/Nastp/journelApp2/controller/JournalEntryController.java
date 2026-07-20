package com.Nastp.journelApp2.controller;

import com.Nastp.journelApp2.entity.JournalEntry;
import com.Nastp.journelApp2.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController
{
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return journalEntryService.getAll();
    }
    @GetMapping("id/{id}")
    public Optional<JournalEntry> getJournalEntryByID(@PathVariable Long id)
    {
        return journalEntryService.getById(id);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry)
    {
        journalEntryService.saveEntry(entry);
        return true;
    }

    @DeleteMapping("id/{Id}")
    public boolean deleteJournalEntryById(@PathVariable Long Id)
    {
        return journalEntryService.deleteById(Id);
    }

    @PutMapping("id/{Id}")
    public boolean updateJournalEntryById(@PathVariable Long Id, @RequestBody JournalEntry entry)
    {
        JournalEntry journalEntry = journalEntryService.getById(Id).orElse(null);
        if (journalEntry != null)
        {
            journalEntry.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : journalEntry.getTitle());
            journalEntry.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : journalEntry.getContent());
            journalEntryService.saveEntry(journalEntry);
            return true;
        }
        return false;
    }
}

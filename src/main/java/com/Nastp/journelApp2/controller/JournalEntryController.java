package com.Nastp.journelApp2.controller;

import com.Nastp.journelApp2.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController
{
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }
    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryByID(@PathVariable Long id)
    {
        return journalEntries.get(id);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry)
    {
        journalEntries.put(entry.getId(), entry);
        return true;
    }

    @DeleteMapping("id/{Id}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long Id)
    {
        return journalEntries.remove(Id);
    }

    @PutMapping("id/{Id}")
    public JournalEntry updateJournalEntryById(@PathVariable Long Id, @RequestBody JournalEntry entry)
    {
        return journalEntries.put(Id, entry);
    }
}

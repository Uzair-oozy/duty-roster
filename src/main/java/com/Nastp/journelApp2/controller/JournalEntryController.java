package com.Nastp.journelApp2.controller;

import com.Nastp.journelApp2.entity.JournalEntry;
import com.Nastp.journelApp2.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController
{
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        try
        {
            List<JournalEntry> journalEntryList = journalEntryService.getAll();
            if(journalEntryList != null && !journalEntryList.isEmpty())
            {
                return new ResponseEntity<>(journalEntryList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception c)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable Long id)
    {
        try
        {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(id);
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        catch (Exception c)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry)
    {
        try
        {
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch(Exception c)
        {
            return new ResponseEntity<>(entry, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{Id}")
    public ResponseEntity<boolean> deleteJournalEntryById(@PathVariable Long Id)
    {
        try
        {
            journalEntryService.deleteById(Id);
            return new ResponseEntity<boolean>(HttpStatus.NO_CONTENT);
        }
        catch (Exception c)
        {
            return new ResponseEntity<boolean>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("id/{Id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable Long Id, @RequestBody JournalEntry entry)
    {
        try
        {
            JournalEntry journalEntry = journalEntryService.getById(Id).orElse(null);
            if (journalEntry != null)
            {
                journalEntry.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : journalEntry.getTitle());
                journalEntry.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : journalEntry.getContent());
                journalEntryService.saveEntry(journalEntry);
                return new ResponseEntity<boolean>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception c)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

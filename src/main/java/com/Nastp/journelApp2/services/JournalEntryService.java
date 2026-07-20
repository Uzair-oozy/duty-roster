package com.Nastp.journelApp2.services;

import com.Nastp.journelApp2.Repository.JournalEntryRepository;
import com.Nastp.journelApp2.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService
{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(Long id)
    {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(Long id)
    {
        journalEntryRepository.deleteById(id);
        return true;
    }
}

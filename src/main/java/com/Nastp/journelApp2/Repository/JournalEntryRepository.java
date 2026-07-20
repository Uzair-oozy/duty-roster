package com.Nastp.journelApp2.Repository;

import com.Nastp.journelApp2.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, Long>
{

}

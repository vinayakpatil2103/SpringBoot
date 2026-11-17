package com.VinayakSpringBoot.journalApplication.service;

import com.VinayakSpringBoot.journalApplication.entity.JournalEntry;
import com.VinayakSpringBoot.journalApplication.entity.User;
import com.VinayakSpringBoot.journalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAll() {

        return journalEntryRepository.findAll();
    }

    public void saveEntry(JournalEntry journalEntry, String username) {

        User user = userService.findByUserName(username);
        System.out.println("Username = " + username);
        System.out.println("User = " + user);

        JournalEntry saved = journalEntryRepository.save(journalEntry);
        System.out.println("Saved ID = " + saved.getId());

        user.getJournalEntries().add(saved);
        System.out.println("List Size = " + user.getJournalEntries().size());

        userService.saveEntry(user);
        System.out.println("User saved successfully");
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        User user=userService.findByUserName(username);
        user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);

    }
}
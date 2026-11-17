package com.VinayakSpringBoot.journalApplication.controller;

import com.VinayakSpringBoot.journalApplication.entity.JournalEntry;
import com.VinayakSpringBoot.journalApplication.entity.User;
import com.VinayakSpringBoot.journalApplication.service.JournalEntryService;
import com.VinayakSpringBoot.journalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/journal")
    public class journalEntryControllerV2 {
        @Autowired
        private JournalEntryService journalEntryService;
        @Autowired
        private UserService userService;

        @GetMapping("/{username}")
        public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
            User user=userService.findByUserName(username);
            List<JournalEntry>all=user.getJournalEntries();
            if(all!=null && !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping("/{username}")
        public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username ){
            try {

                myEntry.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(myEntry,username);
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
            }

        }

        @GetMapping("/id/{myId}")
        public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId){
            Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
            if (journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/id/{username}/{myId}")
        public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String username){
            journalEntryService.deleteById(myId,username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("/id/{username}/{myId}")
        public ResponseEntity updateJournalEntryById(
                @PathVariable ObjectId myId,
                @RequestBody JournalEntry newEntry,
                @PathVariable String username
        )
        {

            JournalEntry old = journalEntryService.findById(myId).orElse(null);

            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.equals("")? newEntry.getContent(): old.getContent());
                return new ResponseEntity<>(old,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




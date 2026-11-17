package com.VinayakSpringBoot.journalApplication.repository;

import com.VinayakSpringBoot.journalApplication.entity.JournalEntry;
import com.VinayakSpringBoot.journalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername (String username);

}


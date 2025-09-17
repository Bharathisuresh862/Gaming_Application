package com.gaming.gamingsystem.repository;

import com.gaming.gamingsystem.entities.Collections;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionsRepository extends MongoRepository<Collections, String> {
    // No custom queries for now
}


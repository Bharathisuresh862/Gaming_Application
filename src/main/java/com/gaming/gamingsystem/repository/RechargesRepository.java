package com.gaming.gamingsystem.repository;

import com.gaming.gamingsystem.entities.Recharges;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargesRepository extends MongoRepository<Recharges, String> {
    // custom queries if needed
}

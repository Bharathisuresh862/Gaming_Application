package com.gaming.gamingsystem.repository;

import com.gaming.gamingsystem.entities.Members;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends MongoRepository<Members, String> {
    // custom queries can be added here later
}

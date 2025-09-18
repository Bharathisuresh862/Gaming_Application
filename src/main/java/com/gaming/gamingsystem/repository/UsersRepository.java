package com.gaming.gamingsystem.repositories;

import com.gaming.gamingsystem.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, String> {
    Optional<Users> findByUsername(String username); // matches your field
}

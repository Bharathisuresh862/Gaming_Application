package com.gaming.gamingsystem.repository;

import com.gaming.gamingsystem.entities.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByNameContainingIgnoreCase(String name);
    List<Game> findByPlayerCount(int playerCount);
    List<Game> findByMinCountLessThanEqualAndMaxCountGreaterThanEqual(int min, int max);
}

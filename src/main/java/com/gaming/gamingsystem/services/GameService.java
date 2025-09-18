package com.gaming.gamingsystem.services;

import com.gaming.gamingsystem.entities.Game;
import com.gaming.gamingsystem.repository.GameRepository;
import com.gaming.gamingsystem.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {   // ✅ Changed name to singular
    private static final Logger log = LoggerFactory.getLogger(GameService.class);
    private final GameRepository repo;

    public GameService(GameRepository repo) {
        this.repo = repo;
    }

    // Create
    public Game create(Game game) {
        log.info("Creating game: {}", game.getName());
        game.setId(null);  // force new insert
        return repo.save(game);
    }

    // Read all
    public List<Game> findAll() {
        log.info("Fetching all games");
        return repo.findAll();
    }

    // Read by id
    public Game findById(String id) {
        log.info("Fetching game by id: {}", id);
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));
    }

    // Update
    public Game update(String id, Game game) {
        Game oldGame = findById(id);  // throws exception if not found
        log.info("Updating game {}", id);
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        return repo.save(oldGame);
    }

    // Delete
    public boolean delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Game not found: " + id);
        }
        log.info("Deleting game {}", id);
        repo.deleteById(id);
        return true;
    }
}

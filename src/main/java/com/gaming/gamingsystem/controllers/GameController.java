package com.gaming.gamingsystem.controllers;

import com.gaming.gamingsystem.entities.Game;
import com.gaming.gamingsystem.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository repo;

    // Create a new game
    @PostMapping
    public Game create(@RequestBody Game game) {
        game.setId(null); // Let MongoDB generate the ID
        return repo.save(game);
    }

    // Get all games
    @GetMapping
    public List<Game> findAll() {
        return repo.findAll();
    }

    // Get game by ID
    @GetMapping("/{id}")
    public Game findById(@PathVariable String id) {
        return repo.findById(id).orElse(null); // ✅ Removed ObjectId
    }

    // Update a game
    @PutMapping("/{id}")
    public Game update(@PathVariable String id, @RequestBody Game game) {
        Optional<Game> optionalGame = repo.findById(id);
        if (optionalGame.isEmpty()) {
            return null; // Or throw exception
        }

        Game oldGame = optionalGame.get();
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        oldGame.setPrice(game.getPrice());
        oldGame.setMinCount(game.getMinCount());
        oldGame.setMaxCount(game.getMaxCount());
        oldGame.setDuration(game.getDuration());
        oldGame.setPlayerCount(game.getPlayerCount());

        return repo.save(oldGame);
    }

    // Delete a game
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<Game> optionalGame = repo.findById(id);
        if (optionalGame.isEmpty()) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}

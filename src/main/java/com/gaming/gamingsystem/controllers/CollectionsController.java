package com.gaming.gamingsystem.controllers;

import com.gaming.gamingsystem.entities.Collections;
import com.gaming.gamingsystem.repository.CollectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private CollectionsRepository collectionsRepo;

    // Create new collection record
    @PostMapping
    public Collections create(@RequestBody Collections collection) {
        collection.setId(null); // let MongoDB generate _id
        return collectionsRepo.save(collection);
    }

    // Get all records
    @GetMapping
    public List<Collections> getAll() {
        return collectionsRepo.findAll();
    }

    // Get by id
    @GetMapping("/{id}")
    public Collections getById(@PathVariable String id) {
        return collectionsRepo.findById(id).orElse(null);
    }

    // Update existing record
    @PutMapping("/{id}")
    public Collections update(@PathVariable String id, @RequestBody Collections updated) {
        return collectionsRepo.findById(id).map(existing -> {
            existing.setDate(updated.getDate());
            existing.setAmount(updated.getAmount());
            return collectionsRepo.save(existing);
        }).orElse(null);
    }

    // Delete record
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (collectionsRepo.existsById(id)) {
            collectionsRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

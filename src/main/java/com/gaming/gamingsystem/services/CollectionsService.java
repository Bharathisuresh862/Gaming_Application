package com.gaming.gamingsystem.services;

import com.gaming.gamingsystem.entities.Collections;
import com.gaming.gamingsystem.repository.CollectionsRepository;
import com.gaming.gamingsystem.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CollectionsService {
    private static final Logger log = LoggerFactory.getLogger(CollectionsService.class);
    private final CollectionsRepository repo;

    public CollectionsService(CollectionsRepository repo) {
        this.repo = repo;
    }

    public Collections create(Collections collection) {
        log.info("Creating collection");
        collection.setId(null);
        return repo.save(collection);
    }

    public List<Collections> findAll() {
        return repo.findAll();
    }

    public Collections findById(String id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Collection not found: " + id));
    }

    public boolean delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Collection not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}

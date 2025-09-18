package com.gaming.gamingsystem.services;

import com.gaming.gamingsystem.entities.Transactions;
import com.gaming.gamingsystem.repository.TransactionsRepository;
import com.gaming.gamingsystem.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionsService {
    private static final Logger log = LoggerFactory.getLogger(TransactionsService.class);
    private final TransactionsRepository repo;

    public TransactionsService(TransactionsRepository repo) {
        this.repo = repo;
    }

    public Transactions create(Transactions tx) {
        log.info("Creating transaction for memberId: {}", tx.getMemberId());
        tx.setId(null);
        return repo.save(tx);
    }

    public List<Transactions> findAll() {
        return repo.findAll();
    }

    public Transactions findById(String id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id));
    }

    public boolean delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}

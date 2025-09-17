package com.gaming.gamingsystem.controllers;

import com.gaming.gamingsystem.entities.Transactions;
import com.gaming.gamingsystem.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsRepository transactionsRepo;

    // Create new transaction
    @PostMapping
    public Transactions create(@RequestBody Transactions tx) {
        tx.setId(null); // Let MongoDB generate ID
        tx.setDateTime(new Date()); // auto set current dateTime
        return transactionsRepo.save(tx);
    }

    // Get all transactions
    @GetMapping
    public List<Transactions> findAll() {
        return transactionsRepo.findAll();
    }

    // Get transaction by ID
    @GetMapping("/{id}")
    public Transactions findById(@PathVariable String id) {
        return transactionsRepo.findById(id).orElse(null);
    }

    // Get transactions by memberId
    @GetMapping("/member/{memberId}")
    public List<Transactions> findByMemberId(@PathVariable String memberId) {
        return transactionsRepo.findByMemberId(memberId);
    }

    // Get transactions by gameId
    @GetMapping("/game/{gameId}")
    public List<Transactions> findByGameId(@PathVariable String gameId) {
        return transactionsRepo.findByGameId(gameId);
    }

    // Delete transaction by ID
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (transactionsRepo.existsById(id)) {
            transactionsRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

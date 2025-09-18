package com.gaming.gamingsystem.services;

import com.gaming.gamingsystem.entities.Recharges;
import com.gaming.gamingsystem.repository.RechargesRepository;
import com.gaming.gamingsystem.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RechargesService {
    private static final Logger log = LoggerFactory.getLogger(RechargesService.class);
    private final RechargesRepository repo;

    public RechargesService(RechargesRepository repo) {
        this.repo = repo;
    }

    // Create
    public Recharges create(Recharges recharge) {
        log.info("Creating recharge for member: {}", recharge.getMemberId());
        recharge.setId(null); // force new insert
        return repo.save(recharge);
    }

    // Read all
    public List<Recharges> findAll() {
        log.info("Fetching all recharges");
        return repo.findAll();
    }

    // Read by id
    public Recharges findById(String id) {
        log.info("Fetching recharge by id: {}", id);
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Recharge not found: " + id));
    }

    // Update
    public Recharges update(String id, Recharges recharge) {
        Recharges oldRecharge = findById(id);  // throws if not found
        log.info("Updating recharge {}", id);
        oldRecharge.setMemberId(recharge.getMemberId());
        oldRecharge.setAmount(recharge.getAmount());
        oldRecharge.setDateTime(recharge.getDateTime());
        return repo.save(oldRecharge);
    }

    // Delete
    public boolean delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Recharge not found: " + id);
        }
        log.info("Deleting recharge {}", id);
        repo.deleteById(id);
        return true;
    }
}

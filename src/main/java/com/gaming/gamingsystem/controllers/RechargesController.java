package com.gaming.gamingsystem.controllers;

import com.gaming.gamingsystem.entities.Recharges;
import com.gaming.gamingsystem.repository.RechargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharges")
public class RechargesController {

    @Autowired
    private RechargesRepository rechargeRepo;

    // Create a recharge
    @PostMapping
    public Recharges create(@RequestBody Recharges recharge) {
        recharge.setId(null); // let MongoDB generate _id
        return rechargeRepo.save(recharge);
    }

    // Get all recharges
    @GetMapping
    public List<Recharges> getAll() {
        return rechargeRepo.findAll();
    }

    // Get recharge by id
    @GetMapping("/{id}")
    public Recharges getById(@PathVariable String id) {
        return rechargeRepo.findById(id).orElse(null);
    }

    // Update recharge
    @PutMapping("/{id}")
    public Recharges update(@PathVariable String id, @RequestBody Recharges updated) {
        return rechargeRepo.findById(id).map(existing -> {
            existing.setMemberId(updated.getMemberId());
            existing.setAmount(updated.getAmount());
            existing.setDateTime(updated.getDateTime());
            return rechargeRepo.save(existing);
        }).orElse(null);
    }

    // Delete recharge
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (rechargeRepo.existsById(id)) {
            rechargeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

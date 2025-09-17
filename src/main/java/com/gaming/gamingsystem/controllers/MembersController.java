package com.gaming.gamingsystem.controllers;

import com.gaming.gamingsystem.entities.Members;
import com.gaming.gamingsystem.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController {

    @Autowired
    private MembersRepository membersRepo;

    // Create new member
    @PostMapping
    public Members create(@RequestBody Members member) {
        member.setId(null); // let MongoDB generate _id
        return membersRepo.save(member);
    }

    // Get all members
    @GetMapping
    public List<Members> getAll() {
        return membersRepo.findAll();
    }

    // Get by id
    @GetMapping("/{id}")
    public Members getById(@PathVariable String id) {
        return membersRepo.findById(id).orElse(null);
    }

    // Update member
    @PutMapping("/{id}")
    public Members update(@PathVariable String id, @RequestBody Members updated) {
        return membersRepo.findById(id).map(existing -> {
            existing.setMemberId(updated.getMemberId());
            existing.setName(updated.getName());
            existing.setPhone(updated.getPhone());
            existing.setBalance(updated.getBalance());
            existing.setJoiningDate(updated.getJoiningDate());
            return membersRepo.save(existing);
        }).orElse(null);
    }

    // Delete member
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (membersRepo.existsById(id)) {
            membersRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

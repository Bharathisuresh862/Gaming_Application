package com.gaming.gamingsystem.controllers;

import com.gaming.gamingsystem.entities.Users;
import com.gaming.gamingsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_users")   // 👈 API endpoint prefix
public class UsersController {

    @Autowired
    private UsersRepository usersRepo;

    // Create new admin user
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        user.setId(null); // Let MongoDB generate _id
        return usersRepo.save(user);
    }

    // Get all admin users
    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable String id) {
        return usersRepo.findById(id).orElse(null);
    }

    // Update user
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable String id, @RequestBody Users user) {
        return usersRepo.findById(id).map(existing -> {
            existing.setUsername(user.getUsername());
            existing.setPassword(user.getPassword());
            existing.setStatus(user.getStatus());
            return usersRepo.save(existing);
        }).orElse(null);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable String id) {
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

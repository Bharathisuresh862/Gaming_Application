package com.gaming.gamingsystem.collections;

import com.gaming.gamingsystem.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dynamic/collection")
public class DynamicCollectionController {

    // Temporary comment to trigger Git

    @Autowired
    private UsersService usersService;

    // GET all documents or by field
    @GetMapping("/{collectionName}")
    public ResponseEntity<?> getDocuments(
            @PathVariable String collectionName,
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String value) {

        if (field != null && value != null) {
            return ResponseEntity.ok(usersService.findByField(collectionName, field, value));
        } else {
            return ResponseEntity.ok(usersService.getAllDocuments(collectionName));
        }
    }

    // POST a new document
    @PostMapping("/{collectionName}")
    public ResponseEntity<?> addDocument(
            @PathVariable String collectionName,
            @RequestBody Map<String, Object> document) {

        return ResponseEntity.ok(usersService.addDocument(collectionName, document));
    }

    // PUT: update document by field
    @PutMapping("/{collectionName}")
    public ResponseEntity<?> updateDocument(
            @PathVariable String collectionName,
            @RequestParam String field,
            @RequestParam String value,
            @RequestBody Map<String, Object> updates) {

        usersService.updateDocument(collectionName, field, value, updates);
        return ResponseEntity.ok("Document updated successfully");
    }

    // DELETE document by field
    @DeleteMapping("/{collectionName}")
    public ResponseEntity<?> deleteDocument(
            @PathVariable String collectionName,
            @RequestParam String field,
            @RequestParam String value) {

        usersService.deleteDocument(collectionName, field, value);
        return ResponseEntity.ok("Document deleted successfully");
    }
}

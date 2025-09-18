package com.gaming.gamingsystem.services;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Get all documents from a collection
    public List<Map> getAllDocuments(String collectionName) {
        return mongoTemplate.findAll(Map.class, collectionName);
    }

    // Get document by a field value
    public Map findByField(String collectionName, String field, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(value));
        return mongoTemplate.findOne(query, Map.class, collectionName);
    }

    // Add a new document
    public Map addDocument(String collectionName, Map<String, Object> document) {
        mongoTemplate.insert(document, collectionName);
        return document;
    }

    // Update document by field
    public void updateDocument(String collectionName, String field, String value, Map<String, Object> updates) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(value));
        Update update = new Update();
        updates.forEach(update::set);
        mongoTemplate.updateFirst(query, update, collectionName);
    }

    // Delete document by field
    public void deleteDocument(String collectionName, String field, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(value));
        mongoTemplate.remove(query, collectionName);
    }
}

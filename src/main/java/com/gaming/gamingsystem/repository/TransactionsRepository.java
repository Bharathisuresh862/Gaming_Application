package com.gaming.gamingsystem.repository;

import com.gaming.gamingsystem.entities.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends MongoRepository<Transactions, String> {

    // Find transactions by memberId
    List<Transactions> findByMemberId(String memberId);

    // Find transactions by gameId
    List<Transactions> findByGameId(String gameId);
}

package com.jurnaliswarga.project.repository;

import com.jurnaliswarga.project.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByStatus(Boolean status);
}

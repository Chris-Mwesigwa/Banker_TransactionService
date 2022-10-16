package com.chris.Banker_TransactionService.repository;

import com.chris.Banker_TransactionService.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

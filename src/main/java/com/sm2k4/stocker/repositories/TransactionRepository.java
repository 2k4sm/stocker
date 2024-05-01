package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

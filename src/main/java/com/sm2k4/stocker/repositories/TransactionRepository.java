package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findAllByStockId(Long stockId);
    Optional<List<Transaction>> findAllByTraderId(Long traderId);
}

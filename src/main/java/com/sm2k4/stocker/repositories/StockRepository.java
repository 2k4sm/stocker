package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}

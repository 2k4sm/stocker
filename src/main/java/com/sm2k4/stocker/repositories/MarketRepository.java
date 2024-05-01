package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}

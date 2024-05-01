package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<Trader, Long> {
}

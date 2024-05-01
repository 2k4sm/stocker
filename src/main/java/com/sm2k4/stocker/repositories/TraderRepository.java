package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraderRepository extends JpaRepository<Trader, Long> {
    Optional<Trader> findTraderByLicno(Long licno);
    Optional<Trader> findTraderById(Long id);
    Optional<Trader> findTraderByContact(String contact);
}

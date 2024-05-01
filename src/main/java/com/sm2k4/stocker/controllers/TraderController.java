package com.sm2k4.stocker.controllers;

import com.sm2k4.stocker.dtos.Traders.CreateTraderDto;
import com.sm2k4.stocker.dtos.Traders.TradersDto;
import com.sm2k4.stocker.dtos.Traders.UpdateTraderDto;
import com.sm2k4.stocker.services.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traders")
public class TraderController {
    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @GetMapping
    public ResponseEntity<List<TradersDto>> getAllTraders() {
        List<TradersDto> traders = traderService.getAllTraders();
        return ResponseEntity.ok(traders);
    }
    @GetMapping("/licno/{licno}")
    public ResponseEntity<TradersDto> getTraderByLicno(@PathVariable Long licno) {
        TradersDto trader = traderService.getTraderByLicno(licno);
        return ResponseEntity.ok(trader);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TradersDto> getTraderById(@PathVariable Long id) {
        TradersDto trader = traderService.getTraderById(id);
        return ResponseEntity.ok(trader);
    }
    @PostMapping
    public ResponseEntity<TradersDto> addTrader(@RequestBody CreateTraderDto createTraderDto) {
        TradersDto trader = traderService.addTrader(createTraderDto);
        return ResponseEntity.status(201).body(trader);
    }
    @PutMapping("/{traderId}")
    public ResponseEntity<TradersDto> updateTrader(@PathVariable Long traderId, @RequestBody UpdateTraderDto updateTraderDto) {
        TradersDto updatedTrader = traderService.updateTrader(traderId, updateTraderDto);
        return ResponseEntity.ok(updatedTrader);
    }

    @DeleteMapping("/{traderId}")
    public ResponseEntity<Void> deleteTrader(@PathVariable Long traderId) {
        traderService.deleteTrader(traderId);
        return ResponseEntity.noContent().build();
    }
}

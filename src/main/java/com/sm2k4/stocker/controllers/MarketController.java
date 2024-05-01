package com.sm2k4.stocker.controllers;


import com.sm2k4.stocker.dtos.Market.MarketRequestDTO;
import com.sm2k4.stocker.dtos.Market.MarketResponseDTO;
import com.sm2k4.stocker.dtos.Market.MarketUpdateDTO;
import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.services.MarketService;
import com.sm2k4.stocker.services.MarketServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/markets")
public class MarketController {

    private final MarketServiceInterface marketService;

    public MarketController(MarketServiceInterface marketService) {
        this.marketService = marketService;
    }

    @GetMapping("")
    public List<MarketResponseDTO> getAllMarkets(){
        List<Market> markets = this.marketService.getAllMarkets();

        List<MarketResponseDTO> marketsResponse = new ArrayList<>();

        for (Market market : markets) {
            marketsResponse.add(market.mapToResponse());
        }

        return marketsResponse;
    }

    @GetMapping("/{id}")
    public MarketResponseDTO getMarketById(@PathVariable("id") Long id){
        Market market = this.marketService.getMarketById(id);

        return market.mapToResponse();
    }

    @PostMapping("")
    public MarketResponseDTO createMarket(@RequestBody MarketRequestDTO newMarket){
        Market market = this.marketService.createMarket(newMarket);
        return market.mapToResponse();
    }

    @PutMapping("/{id}")
    public MarketResponseDTO updateMarket(@PathVariable("id") Long id, @RequestBody MarketUpdateDTO updateMarket){
        Market market = this.marketService.updateMarket(updateMarket,id);
        return market.mapToResponse();
    }

    @DeleteMapping("/{id}")
    public MarketResponseDTO deleteMarket(@PathVariable("id") Long id){
        Market market = this.marketService.deleteMarket(id);
        return market.mapToResponse();
    }
}

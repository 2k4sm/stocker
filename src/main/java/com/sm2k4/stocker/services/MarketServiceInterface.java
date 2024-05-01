package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Market.MarketRequestDTO;
import com.sm2k4.stocker.dtos.Market.MarketUpdateDTO;
import com.sm2k4.stocker.models.Market;

import java.util.List;

public interface MarketServiceInterface {
    public List<Market> getAllMarkets();
    public Market getMarketById(Long id);
    public Market createMarket(MarketRequestDTO marketRequestDTO);
    public Market updateMarket(MarketUpdateDTO updateDTO,Long id);
    public Market deleteMarket(Long id);

}

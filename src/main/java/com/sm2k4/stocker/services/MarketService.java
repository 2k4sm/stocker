package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Market.MarketRequestDTO;
import com.sm2k4.stocker.dtos.Market.MarketUpdateDTO;
import com.sm2k4.stocker.exceptions.GeneralExceptions.AlreadyExistsException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.BadRequestException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.NotFoundException;
import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.repositories.EmployeeRepository;
import com.sm2k4.stocker.repositories.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketService implements MarketServiceInterface {
    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }


    public List<Market> getAllMarkets(){
        List<Market> markets = this.marketRepository.findAll();

        if (markets.isEmpty()) {
            throw new NotFoundException("No markets found");
        }

        return markets;
    }

    public Market getMarketById(Long id) {
        Optional<Market> market = this.marketRepository.findById(id);

        if (market.isEmpty()){
            throw new NotFoundException("No market found with id: "+ id);
        }

        return market.get();
    }

    public Market createMarket(MarketRequestDTO marketRequestDTO) {

        if (marketRequestDTO.getName() == null || marketRequestDTO.getName().isEmpty()) {
            throw new BadRequestException("Market name is required");
        }

        if (marketRequestDTO.getRegion() == null || marketRequestDTO.getRegion().isEmpty()) {
            throw new BadRequestException("Market region is required");
        }

        if (this.marketRepository.findByName(marketRequestDTO.getName()).isPresent()) {
            throw new AlreadyExistsException("Market with name " + marketRequestDTO.getName() + " already exists");
        }

        return this.marketRepository.save(marketRequestDTO.mapToEntity());
    }

    public Market updateMarket(MarketUpdateDTO marketUpdateDTO, Long id) {
        Optional<Market> marketToUpdate = this.marketRepository.findById(id);

        if (marketToUpdate.isEmpty()){
            throw new NotFoundException("No market found with id: "+ id);
        }

        if ((marketUpdateDTO.getName() == null || marketUpdateDTO.getName().isEmpty()) && (marketUpdateDTO.getRegion() == null || marketUpdateDTO.getRegion().isEmpty())) {
            throw new BadRequestException("Market either market name or market region is required");
        }

        Market market = marketToUpdate.get();
        if (marketUpdateDTO.getName() != null ){
            market.setName(marketUpdateDTO.getName());
        }

        if (marketUpdateDTO.getRegion() != null){
            market.setRegion(marketUpdateDTO.getRegion());
        }

        if (this.marketRepository.findByName(marketUpdateDTO.getName()).isPresent()) {
            throw new AlreadyExistsException("Market with name " + marketUpdateDTO.getName() + " already exists");
        }

        return this.marketRepository.save(market);
    }

    public Market deleteMarket(Long id) {
        Optional<Market> market = this.marketRepository.findById(id);

        if (market.isEmpty()){
            throw new NotFoundException("No market found with id: "+ id);
        }

        Market marketToDelete = market.get();

        marketRepository.delete(marketToDelete);

        return marketToDelete;
    }
}

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MarketService implements MarketServiceInterface {
    private final MarketRepository marketRepository;
    private final EmployeeRepository employeeRepository;
    public MarketService(MarketRepository marketRepository , EmployeeRepository employeeRepository) {
        this.marketRepository = marketRepository;
        this.employeeRepository = employeeRepository;
    }


    public List<Market> getAllMarkets(){
        List<Market> markets = this.marketRepository.findAll();

        if (markets.isEmpty()) {
            log.warn("No markets found");
            throw new NotFoundException("No markets found");
        }


        log.info("Found {} markets", markets.size());

        return markets;
    }

    public Market getMarketById(Long id) {
        Optional<Market> market = this.marketRepository.findById(id);

        if (market.isEmpty()){
            log.warn("No market found with id {}", id);
            throw new NotFoundException("No market found with id: "+ id);
        }

        log.info("Found market with id {}", id);
        return market.get();
    }

    public Market createMarket(MarketRequestDTO marketRequestDTO) {

        if (marketRequestDTO.getName() == null || marketRequestDTO.getName().isEmpty()) {
            log.warn("Market name is empty");
            throw new BadRequestException("Market name is required");
        }

        if (marketRequestDTO.getRegion() == null || marketRequestDTO.getRegion().isEmpty()) {
            log.warn("Market region is empty");
            throw new BadRequestException("Market region is required");
        }

        if (this.marketRepository.findByName(marketRequestDTO.getName()).isPresent()) {
            log.warn("Market with name {} already exists", marketRequestDTO.getName());
            throw new AlreadyExistsException("Market with name " + marketRequestDTO.getName() + " already exists");
        }

        log.info("Creating market {}", marketRequestDTO.getName());
        return this.marketRepository.save(marketRequestDTO.mapToEntity());
    }

    public Market updateMarket(MarketUpdateDTO marketUpdateDTO, Long id) {
        Optional<Market> marketToUpdate = this.marketRepository.findById(id);

        if (marketToUpdate.isEmpty()){
            log.warn("No market found with id {}", id);
            throw new NotFoundException("No market found with id: "+ id);
        }

        if ((marketUpdateDTO.getName() == null || marketUpdateDTO.getName().isEmpty()) && (marketUpdateDTO.getRegion() == null || marketUpdateDTO.getRegion().isEmpty())) {
            log.warn("Either market name or market region is required");
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
            log.warn("Market with name {} already exists", marketUpdateDTO.getName());
            throw new AlreadyExistsException("Market with name " + marketUpdateDTO.getName() + " already exists");
        }

        log.info("Updating market {}", marketUpdateDTO.getName());
        return this.marketRepository.save(market);
    }

    public Market deleteMarket(Long id) {
        Optional<Market> market = this.marketRepository.findById(id);

        if (market.isEmpty()){
            log.warn("No market found with id {}", id);
            throw new NotFoundException("No market found with id: "+ id);
        }

        Market marketToDelete = market.get();

        marketRepository.delete(marketToDelete);

        log.info("Deleted market {}", marketToDelete.getId());
        return marketToDelete;
    }
}

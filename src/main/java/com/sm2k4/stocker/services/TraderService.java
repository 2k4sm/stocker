package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Traders.CreateTraderDto;
import com.sm2k4.stocker.dtos.Traders.TradersDto;
import com.sm2k4.stocker.dtos.Traders.UpdateTraderDto;
import com.sm2k4.stocker.exceptions.GeneralExceptions.NotFoundException;
import com.sm2k4.stocker.models.Trader;
import com.sm2k4.stocker.repositories.TraderRepository;
import org.springframework.stereotype.Service;
import com.sm2k4.stocker.commons.Utils;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraderService {
    private final TraderRepository traderRepository;
    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public List<TradersDto> getAllTraders() {
        return traderRepository.findAll().stream().map(trader -> new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(),trader.getTransList())).collect(Collectors.toList());
    }
    public TradersDto getTraderByLicno(Long licno) {
        Trader trader = traderRepository.findTraderByLicno(licno).orElseThrow(() -> new NotFoundException("Trader not found with trading license number: " + licno));
        return new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(), trader.getTransList());
    }
    public TradersDto addTrader(CreateTraderDto createTraderDto) {
        Utils utils = new Utils();
        Long licno = utils.generateLiccno();
         Trader trader = new Trader(createTraderDto.getName(), createTraderDto.getContact(), licno);
            traderRepository.save(trader);
            return new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(), trader.getTransList());

    }
    public void deleteTrader(Long id) {
        Trader trader = traderRepository.findById(id).orElseThrow(() -> new RuntimeException("Trader not found"));
        traderRepository.delete(trader);
    }
    public TradersDto updateTrader(Long id, UpdateTraderDto updateTraderDto){
        Trader trader = traderRepository.findById(id).orElseThrow(() -> new RuntimeException("Trader not found"));

        trader.setName(updateTraderDto.getName());
        trader.setContact(updateTraderDto.getContact());

        Trader updatedtrader = traderRepository.save(trader);

        return new TradersDto(updatedtrader.getId(), updatedtrader.getName(), updatedtrader.getLicno(), updatedtrader.getContact(), updatedtrader.getTransList());
    }


}

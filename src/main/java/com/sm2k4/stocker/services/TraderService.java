package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Traders.CreateTraderDto;
import com.sm2k4.stocker.dtos.Traders.TradersDto;
import com.sm2k4.stocker.dtos.Traders.UpdateTraderDto;
import com.sm2k4.stocker.exceptions.GeneralExceptions.AlreadyExistsException;
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
    private final TransactionService transactionService;
    public TraderService(TraderRepository traderRepository,TransactionService transactionService){
        this.traderRepository = traderRepository;
        this.transactionService = transactionService;
    }

    public List<TradersDto> getAllTraders() {
          List<Trader> traders = traderRepository.findAll();
            return traders.stream().map(trader -> new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(), transactionService.getAllTransactionsByTraderId(trader.getId()))).collect(Collectors.toList());
    }
    public TradersDto getTraderByLicno(Long licno) {
        Trader trader = traderRepository.findTraderByLicno(licno).orElseThrow(() -> new NotFoundException("Trader not found with trading license number: " + licno));
        return new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(), transactionService.getAllTransactionsByTraderId(trader.getId()));
    }
    public TradersDto getTraderById(Long id) {
        try{
            Trader trader = traderRepository.findTraderById(id).orElseThrow(() -> new NotFoundException("Trader not found with id: " + id));
            return new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(), transactionService.getAllTransactionsByTraderId(trader.getId()));
        }catch (Exception e){
            throw new NotFoundException("Trader not found with id: " + id);
        }
    }
    public TradersDto addTrader(CreateTraderDto createTraderDto) {
        Trader trader = traderRepository.findTraderByContact(createTraderDto.getContact()).orElse(null);
        Utils utils = new Utils();
        Long licno = utils.generateLiccno();
        if(trader != null){
            throw new AlreadyExistsException("Trader already exists with contact: " + createTraderDto.getContact());
        }else{
            trader = new Trader(createTraderDto.getName(), createTraderDto.getContact(), licno);
            traderRepository.save(trader);
        }
            return new TradersDto(trader.getId(), trader.getName(), trader.getLicno(), trader.getContact(), transactionService.getAllTransactionsByTraderId(trader.getId()));

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

package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Stock.StockRequestDTO;
import com.sm2k4.stocker.dtos.Stock.StockUpdateDTO;
import com.sm2k4.stocker.exceptions.GeneralExceptions.AlreadyExistsException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.BadRequestException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.NotFoundException;
import com.sm2k4.stocker.models.*;
import com.sm2k4.stocker.repositories.MarketRepository;
import com.sm2k4.stocker.repositories.StockRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Primary
public class StockServiceImpl implements StockService{

    StockRepository stockRepository;
    MarketRepository marketRepository;

    public StockServiceImpl(StockRepository stockRepository , MarketRepository marketRepository){
        this.stockRepository = stockRepository;
        this.marketRepository = marketRepository;
    }

    @Override
    public Stock getStockById(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if(optionalStock.isEmpty()){
            throw new NotFoundException("Stock with id " + id + " not found");
        }
        return optionalStock.get();
    }

    @Override
    public List<Stock> getAllStocks() {
        if (stockRepository.findAll().isEmpty()){
            throw new NotFoundException("No stocks found");
        }
        return stockRepository.findAll();
    }


    @Override
    public Stock createStock(StockRequestDTO stockRequestDTO) {
        List<Long> marketIdList = stockRequestDTO.getMarketList();
        List<Market> marketListToAdd = new ArrayList<>();

        List<Stock> stockList = stockRepository.findAll();
        for(Stock stock : stockList){
            if(stock.getName().equals(stockRequestDTO.getName())){
                throw new AlreadyExistsException("Stock with name " + stockRequestDTO.getName());
            }
        }

        for(Long marketId : marketIdList){
            Optional<Market> optionalMarket = marketRepository.findById(marketId);
            if(optionalMarket.isEmpty()){
                throw new NotFoundException("Market with id " + marketId + " not found");
            }else {
                marketListToAdd.add(optionalMarket.get());
            }
        }

        if(stockRequestDTO.getName() == null){
            throw new BadRequestException("Stock name is required");
        }
        if(stockRequestDTO.getPrice() == null){
            throw new BadRequestException("Stock price is required");
        }
        if(stockRequestDTO.getMarketList() == null){
            throw new BadRequestException("Stock market list is required");
        }

        Stock stock = new Stock();
        stock.setName(stockRequestDTO.getName());
        stock.setPrice(stockRequestDTO.getPrice());
        stock.setMarketList(marketListToAdd);

        return this.stockRepository.save(stock);
    }


    @Override
    public void deleteStock(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if(optionalStock.isEmpty()){
            throw new NotFoundException("Stock with id " + id + " not found");
        }
        this.stockRepository.deleteById(id);
    }

    @Override
    public Stock updateStock(Long stockId, StockUpdateDTO stockUpdateDTO) {
        Optional<Stock> stock = stockRepository.findById(stockId);
        String name = stockUpdateDTO.getName();
        Long price = stockUpdateDTO.getPrice();
        if(stock.isEmpty()){
            throw new NotFoundException("Stock with id " + stockId + " not found");
        }



        if(stockUpdateDTO.getName() == null && stockUpdateDTO.getPrice() == null){
            throw new BadRequestException("Name & price is required");
        }

        if(stock.get().getName().equals(name) && stock.get().getPrice().equals(price)){
            throw new AlreadyExistsException("Stock with name " + name + " and price " + price + " already exists");
        }

        if(stockUpdateDTO.getName() == null && stockUpdateDTO.getPrice() != null){
            stock.get().setPrice(stockUpdateDTO.getPrice());
            this.stockRepository.save(stock.get());
            return stock.get();
        }
        if(stockUpdateDTO.getPrice() == null && stockUpdateDTO.getName() !=null){
            stock.get().setName(stockUpdateDTO.getName());
            this.stockRepository.save(stock.get());
            return stock.get();
        }

        stock.get().setName(stockUpdateDTO.getName());
        stock.get().setPrice(stockUpdateDTO.getPrice());
        this.stockRepository.save(stock.get());
        return stock.get();
    }

}
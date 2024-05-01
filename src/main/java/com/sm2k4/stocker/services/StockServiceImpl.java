package com.sm2k4.stocker.services;

import com.sm2k4.stocker.models.*;
import com.sm2k4.stocker.repositories.MarketRepository;
import com.sm2k4.stocker.repositories.StockRepository;
import com.sm2k4.stocker.repositories.TraderRepository;
import com.sm2k4.stocker.repositories.TransactionRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StockServiceImpl implements StockService{

    StockRepository stockRepository;


    public StockServiceImpl(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock getStockById(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if(optionalStock.isEmpty()){
//            throw new StockNotFoundException(id, "Stock not found");
            throw new RuntimeException("Stock not found");
        }
        return optionalStock.get();
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if(optionalStock.isEmpty()){
            //throw new StockNotFoundException(id, "Stock not found");
            throw new RuntimeException("Stock not found");
        }
        stockRepository.save(stock); // save method is used to update the stock provided by JPA repository
        return stock;
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public Stock updateStockPrice(Long stockId, Long newPrice) {
        Optional<Stock> stock = stockRepository.findById(stockId);
        if(stock.isPresent()){
            stock.get().setPrice(newPrice);
            stockRepository.save(stock.get());
            return stock.get();
        }
        throw new RuntimeException("Stock not found");
    }

    @Override
    public Stock updateStockName(Long stockId, String newName) {
        Optional<Stock> stock = stockRepository.findById(stockId);
        if(stock.isPresent()){
            stock.get().setName(newName);
            stockRepository.save(stock.get());
            return stock.get();
        }
        throw new RuntimeException("Stock not found");
    }
}

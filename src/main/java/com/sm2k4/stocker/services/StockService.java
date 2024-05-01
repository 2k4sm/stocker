package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Stock.StockRequestDTO;
import com.sm2k4.stocker.dtos.Stock.StockUpdateDTO;
import com.sm2k4.stocker.models.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {

    public Stock getStockById(Long id);
    public List<Stock> getAllStocks();
    public Stock createStock(StockRequestDTO stockRequestDTO);
    public void deleteStock(Long id);
    public Stock updateStock(Long stockId, StockUpdateDTO stockUpdateDTO);

}
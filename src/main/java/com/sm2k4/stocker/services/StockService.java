package com.sm2k4.stocker.services;

import com.sm2k4.stocker.models.Stock;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public interface StockService {
    //CRUD operations
    public Stock getStockById(Long id);
    public List<Stock> getAllStocks();
    public Stock createStock(Stock stock);
    public Stock updateStock(Long id, Stock stock);
    public void deleteStock(Long id);

    public Stock updateStockPrice(Long stockId, Long newPrice);
    public Stock updateStockName(Long stockId, String newName);

}

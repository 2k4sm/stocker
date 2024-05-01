package com.sm2k4.stocker.controllers;


import com.sm2k4.stocker.dtos.Stock.StockRequestDTO;
import com.sm2k4.stocker.dtos.Stock.StockResponseDTO;
import com.sm2k4.stocker.dtos.Stock.StockUpdateDTO;
import com.sm2k4.stocker.models.Stock;
import com.sm2k4.stocker.services.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("")
    public List<StockResponseDTO> getAllStocks(){
        List<Stock> stocks = this.stockService.getAllStocks();
        List<StockResponseDTO> stocksResponse = new ArrayList<>();

        for(Stock stock : stocks){
            stocksResponse.add(stock.mapToResponse());
        }

        return stocksResponse;
    }

    @GetMapping("/{id}")
    public StockResponseDTO getStockById(@PathVariable Long id){
        Stock stock = this.stockService.getStockById(id);
        return stock.mapToResponse();
    }

    @PostMapping("")
    public StockResponseDTO createStock(@RequestBody StockRequestDTO newStock){
        Stock stock = this.stockService.createStock(newStock);
        return stock.mapToResponse();
    }

    @PutMapping("/{id}")
    public StockResponseDTO updateStock(@PathVariable Long id, @RequestBody StockUpdateDTO stockUpdateDTO){
        Stock stock = this.stockService.updateStock(id, stockUpdateDTO);
        return stock.mapToResponse();
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id){
        this.stockService.deleteStock(id);
    }

}
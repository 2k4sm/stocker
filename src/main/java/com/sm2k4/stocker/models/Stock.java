package com.sm2k4.stocker.models;

import com.sm2k4.stocker.dtos.Market.MarketResponseDTO;
import com.sm2k4.stocker.dtos.Stock.StockResponseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Market> marketList;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Transaction> transList;

    @Column(nullable = false)
    private Long price;


    //private List<Long> historicalPrices;

    public StockResponseDTO mapToResponse(){
        StockResponseDTO stockResponse = new StockResponseDTO();
        stockResponse.setId(id);
        stockResponse.setPrice(price);
        stockResponse.setName(name);
        stockResponse.setMarketList(marketList);
        stockResponse.setMarketList(marketList);
        stockResponse.setTransList(transList);
        return stockResponse;
    }
}

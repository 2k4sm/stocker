package com.sm2k4.stocker.dtos.Stock;

import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class StockResponseDTO {
    private Long id;
    private String name;
    private Long price;
    private List<Market> marketList;
    private List<Transaction> transList;
}

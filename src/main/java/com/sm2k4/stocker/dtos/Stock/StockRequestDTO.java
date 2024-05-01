package com.sm2k4.stocker.dtos.Stock;

import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.models.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class StockRequestDTO {
    private String name;
    private Long price;
    private List<Long> marketList;
}

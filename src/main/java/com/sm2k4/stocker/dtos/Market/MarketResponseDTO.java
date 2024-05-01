package com.sm2k4.stocker.dtos.Market;


import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.models.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MarketResponseDTO {
    private Long id;
    private String name;
    private String region;

}

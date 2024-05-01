package com.sm2k4.stocker.dtos.Market;

import com.sm2k4.stocker.models.Market;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketUpdateDTO {
    private String name;
    private String region;
}

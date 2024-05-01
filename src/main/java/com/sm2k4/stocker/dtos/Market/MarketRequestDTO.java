package com.sm2k4.stocker.dtos.Market;


import com.sm2k4.stocker.models.Market;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MarketRequestDTO {
    private String name;
    private String region;

    public Market mapToEntity(){
        Market market = new Market();
        market.setName(name);
        market.setRegion(region);

        return market;
    }
}

package com.sm2k4.stocker.models;

import com.sm2k4.stocker.dtos.Market.MarketResponseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "market")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String region;


    public MarketResponseDTO mapToResponse(){
        MarketResponseDTO marketResponse = new MarketResponseDTO();
        marketResponse.setId(id);
        marketResponse.setName(name);
        marketResponse.setRegion(region);
        return marketResponse;
    }
}

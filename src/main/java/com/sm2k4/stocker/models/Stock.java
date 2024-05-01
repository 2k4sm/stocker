package com.sm2k4.stocker.models;

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
    @ManyToMany
    private List<Market> marketList;
    @OneToMany
    private List<Transaction> transList;
    @Column(nullable = false)
    private Long price;


    //private List<Long> historicalPrices;
}

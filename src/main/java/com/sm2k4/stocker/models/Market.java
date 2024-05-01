package com.sm2k4.stocker.models;

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
    @Column(unique = true, nullable = false)
    private String region;
    @ManyToMany
    private List<Stock> stockList;
    @OneToMany
    private List<Employee> employeeList;
}

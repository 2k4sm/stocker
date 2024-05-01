package com.sm2k4.stocker.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Stock stockId;

    @ManyToOne
    private Trader traderId;

    @Column(nullable = false)
    private Long qty;
    @Column(nullable = false)
    private Type type;
    @Column(nullable = false)
    private Date date;
}

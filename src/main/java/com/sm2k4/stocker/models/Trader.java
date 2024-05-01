package com.sm2k4.stocker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "trader")
@NoArgsConstructor
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false,updatable = false)
    private Long licno;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transList;

  public Trader(String name, String contact, Long licno) {
    this.name = name;
    this.contact = contact;
    this.licno = licno;
  }
}

package com.sm2k4.stocker.dtos.Traders;

import com.sm2k4.stocker.models.Transaction;

import java.util.List;

public class TradersDto {
    private Long id;
    private String name;
    private Long licno;
    private String contact;
    private List<Transaction> transList;

    public TradersDto(Long id, String name, Long licno, String contact, List<Transaction> transList) {
        this.id = id;
        this.name = name;
        this.licno = licno;
        this.contact = contact;
        this.transList = transList;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getLicno() {
        return licno;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public List<Transaction> getTransList() {
        return transList;
    }
    public void setTransList(List<Transaction> transList) {
        this.transList = transList;
    }


}

package com.sm2k4.stocker.dtos.Traders;

public class UpdateTraderDto {
    private String name;
    private String contact;

    public UpdateTraderDto() {
    }

    public UpdateTraderDto(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }


}

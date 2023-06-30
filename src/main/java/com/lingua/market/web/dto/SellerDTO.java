package com.lingua.market.web.dto;

import com.lingua.market.model.Address;

public class SellerDTO {
    private Long id;
    private Long userId;
    private Address address;

    // Getters and Setters
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Address getAddress() {
        return this.address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}

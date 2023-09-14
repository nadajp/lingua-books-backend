package com.lingua.market.web.dto;

public class SellerDTO {
    private Long id;
    private Long userId;

    private String addressStreet;
    private String addressNumber;
    private String addressCity;
    private String addressState;
    private String addressZip;
    private String addressCountry;

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
    public String getAddressStreet() {
        return this.addressStreet;
    }
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
    public String getAddressNumber() {
        return this.addressNumber;
    }   
    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }
    public String getAddressCity() {
        return this.addressCity;
    }
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
    public String getAddressState() {
        return this.addressState;
    }
    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }
    public String getAddressZip() {
        return this.addressZip;
    }
    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }
    public String getAddressCountry() {
        return this.addressCountry;
    }
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }
}

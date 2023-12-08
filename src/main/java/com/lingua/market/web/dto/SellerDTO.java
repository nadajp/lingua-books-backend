package com.lingua.market.web.dto;

public class SellerDTO {
    private Long id;
    private String authUser;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String stripeAccountId;
    private String stripeStatus;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthUser() {
        return this.authUser;
    }
    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostalCode() {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getStripeAccountId() {
        return this.stripeAccountId;
    }
    public void setStripeAccountId(String stripeAccountId) {
        this.stripeAccountId = stripeAccountId;
    }
    public String getStripeStatus() {
        return this.stripeStatus;
    }
    public void setStripeStatus(String stripeStatus) {
        this.stripeStatus = stripeStatus;
    }
}

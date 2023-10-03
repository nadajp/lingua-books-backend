package com.lingua.market.persistence.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String authUser;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_number")
    private String addressNumber;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_state")
    private String addressState;

    @Column(name = "address_zip")
    private String addressZip;

    @Column(name = "address_country")
    private String addressCountry;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }
    public String getAuthUser() {
        return this.authUser;
    }
    public Instant getCreatedAt() {
        return this.createdAt;
    }
    public Instant getUpdatedAt() {
        return this.updatedAt;
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
    public void setAddressSate(String addressState) {
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

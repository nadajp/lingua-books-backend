package com.lingua.market.persistence.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String authUser;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "display_name")
    private String displayName;

    private String city;

    private String state;

    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "stripe_account_id")
    private String stripeAccountId;

    @Column(name = "stripe_status")
    @Enumerated(EnumType.STRING)
    private StripeStatus stripeStatus;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return this.user;
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
    public StripeStatus getStripeStatus() {
        return this.stripeStatus;
    }
    public void setStripeStatus(StripeStatus stripeStatus) {
        this.stripeStatus = stripeStatus;
    }
}

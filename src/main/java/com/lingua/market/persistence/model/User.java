package com.lingua.market.persistence.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authUserId; 

    private String username;

    private String email;
    
    @Column(name = "locale")
    private String locale;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "default_languages")
    private List<Long> defaultLanguages;

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getLocale() {
        return this.locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public Instant getCreatedAt() {
        return this.createdAt;
    }
    public Instant getUpdatedAt() {
        return this.updatedAt;
    }
    public List<Long> getDefaultLanguages() {
        return this.defaultLanguages;
    }
    public void setDefaultLanguages(List<Long> defaultLanguages) {
        this.defaultLanguages = defaultLanguages;
    }
    public String getAuthUserId() {
        return this.authUserId;
    }
    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

package com.lingua.market.persistence.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_settings")
public class UserSettings {
    @Id
    private Long id;

    private String authUserId;
    
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
}

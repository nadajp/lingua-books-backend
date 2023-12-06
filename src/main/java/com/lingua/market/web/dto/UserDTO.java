package com.lingua.market.web.dto;

import java.util.List;

public class UserDTO {

    private String authUserId; 
    private String username;
    private List<Long> defaultLanguages;
    private String email;

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
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


package com.lingua.market.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    private String phone;

    //private String locale;

    @Column(name = "profile_picture")
    private String profilePicture;

    // @Column(name = "last_login")
    // private LocalDateTime lastLogin;
    
    @Column(name = "display_name")
    private String displayName;
    
    // Constructors
    public User() {}
    
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // public String getLocale() {
    //     return locale;
    // }

    // public void setLocale(String locale) {
    //     this.locale = locale;
    // }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    // public LocalDateTime getLastLogin() {
    //     return lastLogin;
    // }

    // public void setLastLogin(LocalDateTime lastLogin) {
    //     this.lastLogin = lastLogin;
    // }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

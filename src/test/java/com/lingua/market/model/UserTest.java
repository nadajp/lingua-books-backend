package com.lingua.market.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserTest {
    
    @Test
    public void testGettersAndSetters() {
        User user = new User();
        
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
        
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
        
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());
        
        user.setPhone("1234567890");
        assertEquals("1234567890", user.getPhone());
        
        user.setProfilePicture("path/to/picture.png");
        assertEquals("path/to/picture.png", user.getProfilePicture());
        
        user.setDisplayName("John D.");
        assertEquals("John D.", user.getDisplayName());
    }
    
    @Test
    public void testConstructor() {
        User user = new User("John", "Doe", "john.doe@example.com");
        
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
    }
    
    
    // @Test
    // public void testLocale() {
    //     User user = new User();
        
    //     user.setLocale("en-US");
    //     assertEquals("en-US", user.getLocale());
    // }
    
    // @Test
    // public void testLastLogin() {
    //     User user = new User();
        
    //     LocalDateTime now = LocalDateTime.now();
    //     user.setLastLogin(now);
    //     assertEquals(now, user.getLastLogin());
    // }
    
}


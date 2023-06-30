package com.lingua.market.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.lingua.market.persistence.model.User;

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
    
}


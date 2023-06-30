package com.lingua.market.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.Test;

import com.lingua.market.persistence.model.Seller;
import com.lingua.market.persistence.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class SellerTest {

    @Test
    public void test_Seller() {
        Seller seller = new Seller();
        User user = new User();
        // user.setId(1L);
        // seller.setUser(user);
        // seller.setAddress("New York");
        // seller.setLatitude(40.730610);
        // seller.setLongitude(-73.935242);
        // assertNotNull(seller.getCreatedAt());
        // assertNotNull(seller.getUpdatedAt());
        // assertEquals("New York", seller.getAddress());
        // assertEquals(40.730610, seller.getLatitude());
        // assertEquals(-73.935242, seller.getLongitude());
    }
}


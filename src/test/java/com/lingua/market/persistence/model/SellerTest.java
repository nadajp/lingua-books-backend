package com.lingua.market.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class SellerTest {

    @Test
    public void test_Seller() {
        Seller seller = new Seller();

        seller.setAddressCity("New York");

        assertEquals("New York", seller.getAddressCity());

    }
}


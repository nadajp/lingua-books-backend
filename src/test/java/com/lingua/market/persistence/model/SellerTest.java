package com.lingua.market.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SellerTest {

    @Test
    public void test_Seller() {
        Seller seller = new Seller();

        seller.setCity("Naples");
        seller.setState("FL");
        seller.setCountry("US");
        assertEquals("Naples", seller.getCity());
        assertEquals("FL", seller.getState());
        assertEquals("US", seller.getCountry());
    }
}


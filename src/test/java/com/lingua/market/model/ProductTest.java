package com.lingua.market.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void testGetterAndSetterMethods() {
        Product product = new Product();

        // Set properties using setter methods
        product.setName("The Hobbit");
        product.setTitle("J.R.R. Tolkien");
        product.setLanguage("English");
        product.setPrice(15.99);
        product.setSellerId(3457L);
        product.setId(1L);

        // Check if getter methods return the correct values
        assertEquals("The Hobbit", product.getName());
        assertEquals("J.R.R. Tolkien", product.getAuthor());
        assertEquals("English", product.getLanguage());
        assertEquals(15.99, product.getPrice());
        assertEquals(3457L, product.getSellerId());
        assertEquals(1L, product.getId());
    }
}

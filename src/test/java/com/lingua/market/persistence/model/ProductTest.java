package com.lingua.market.persistence.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class ProductTest {
    @Test
    public void testGetterAndSetterMethods() {
        Product product = new Product();

        product.setName("The Hobbit");
        product.setTitle("J.R.R. Tolkien");
        product.setPrice(15.99);
        product.setSellerId("auth0|12345");
        product.setId(1L);

        assertEquals("The Hobbit", product.getName());
        assertEquals("J.R.R. Tolkien", product.getAuthor());
        assertEquals(15.99, product.getPrice());
        assertEquals(3457L, product.getSellerId());
        assertEquals(1L, product.getId());
    }
}

package com.lingua.market.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    private Category category;
    private List<Subcategory> subcategories;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");
        category.setSlug("test-category");

        subcategories = new ArrayList<>();
        Subcategory subcategory1 = new Subcategory();
        subcategory1.setId(1L);
        subcategory1.setName("Test Subcategory 1");
        subcategory1.setSlug("test-subcategory-1");
        subcategory1.setParent(category);
        subcategories.add(subcategory1);

        Subcategory subcategory2 = new Subcategory();
        subcategory2.setId(2L);
        subcategory2.setName("Test Subcategory 2");
        subcategory2.setSlug("test-subcategory-2");
        subcategory2.setParent(category);
        subcategories.add(subcategory2);

        category.setSubcategories(subcategories);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, category.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testGetSlug() {
        assertEquals("test-category", category.getSlug());
    }

    @Test
    public void testGetSubcategories() {
        assertNotNull(category.getSubcategories());
        assertEquals(2, category.getSubcategories().size());
    }
}
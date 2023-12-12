package com.lingua.market.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingua.market.service.CategoryService;
import com.lingua.market.web.controller.CategoryController;
import com.lingua.market.web.dto.CategoryDTO;
import com.lingua.market.web.dto.SubcategoryDTO;

@WebMvcTest(CategoryController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    CategoryController categoryController;

    @Test
    public void getAllCategoriesTest() throws Exception {
        when(categoryService.getAllCategoriesWithSubcategories())
        .thenReturn(this.getMockCategories());

         MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/categories"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(this.getMockCategories()), response);
    }

    @Test
    public void getCategoryBySlugTest() throws Exception {
        when(categoryService.getCategoryBySlug("nonfiction"))
        .thenReturn(17L);

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/categories/nonfiction"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals("17", response);
    }

    @Test
        public void createCategoryTest() throws Exception {
            CategoryDTO category = this.getMockCategory();
            when(categoryService.createCategory("Nonfiction", "nonfiction"))
            .thenReturn(category);

            String categoryJson = new ObjectMapper().writeValueAsString(category);

            MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/categories")
                .contentType("application/json")
                .content(categoryJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

            String response = result.getResponse().getContentAsString();
            assertEquals(categoryJson, response);
        }
    @Test
    public void createSubcategoryTest() throws Exception {
        SubcategoryDTO subcategory = this.getMockSubcategory();
        when(categoryService.createSubcategory("Health", "health", 17L))
        .thenReturn(subcategory);

        String subcategoryJson = new ObjectMapper().writeValueAsString(subcategory);

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/subcategories")
            .contentType("application/json")
            .content(subcategoryJson))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(subcategoryJson, response);
    }

    private List<CategoryDTO> getMockCategories() {
        return List.of(this.getMockCategory());
    }
    private CategoryDTO getMockCategory() {
        CategoryDTO category = new CategoryDTO();
        category.setId(17L);
        category.setName("Nonfiction");
        category.setSlug("nonfiction");
        category.setSubcategories(List.of(getMockSubcategory()));
        return category;
    }
    private SubcategoryDTO getMockSubcategory() {
        SubcategoryDTO subcategory = new SubcategoryDTO();
        subcategory.setId(1234L);
        subcategory.setName("Health");
        subcategory.setSlug("health");
        subcategory.setParentCategoryId(17L);
        return subcategory;
    }
}
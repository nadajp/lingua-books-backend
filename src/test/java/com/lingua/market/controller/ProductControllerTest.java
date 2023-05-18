package com.lingua.market.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingua.market.model.Product;
import com.lingua.market.model.ProductDto;
import com.lingua.market.repository.ProductRepository;
import com.lingua.market.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc()
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    ProductController productController;

    @Test
    public void createProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                                    "file", "filename.txt", 
                                    MediaType.IMAGE_JPEG_VALUE,
                                    "test data".getBytes());
        
        ProductDto product = new ProductDto("Romeo and Juliette", 
                                            "Shakespeare", 
                                            10.99, 
                                            "Good", 
                                            "English", 
                                            1L, 1L, 345L);

        when(productService.createProduct(product, file)).thenReturn(product);


        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.multipart("/api/v1/products")
                .file("image", file.getBytes())
                .contentType(MULTIPART_FORM_DATA)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();


        String response = result.getResponse().getContentAsString();
        assertEquals(response, new ObjectMapper().writeValueAsString(product));
    }

    @Test
    public void getAllProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product("Romeo and Juliette", "Shakespeare", "English", 10.99, 123L));

        when(productRepository.findAll()).thenReturn(products);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(response, new ObjectMapper().writeValueAsString(products));
    }
}

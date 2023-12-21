package com.lingua.market.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingua.market.web.dto.ProductDTO;
import com.lingua.market.persistence.dao.ProductRepository;
import com.lingua.market.persistence.model.Language;
import com.lingua.market.persistence.model.Product;
import com.lingua.market.service.ProductService;
import com.lingua.market.web.controller.ProductController;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    ProductController productController;

    @Test
    public void createProduct() throws Exception {
        Language language = new Language(1L, "Croatian");
        
        ProductDTO product = new ProductDTO("Osmi Povjerenik", 
                                            "Renato Baretic", 
                                            10.99, 
                                            "Good", 
                                            language, 
                                            1L, 1L, "auth0|12345");

        when(productService.createProduct(any(ProductDTO.class), any(MultipartFile.class)))
            .thenReturn(product); 

        String productJson = new ObjectMapper().writeValueAsString(product);

        MockMultipartFile productPart = new MockMultipartFile(
            "product", 
            "", 
            "application/json", 
            productJson.getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile filePart = new MockMultipartFile(
            "image",
            "filename.txt",
            MediaType.IMAGE_JPEG_VALUE,
            "test data".getBytes()
        );

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.multipart("/api/v1/products")
                    .file(productPart)
                    .file(filePart)
                    .contentType(MULTIPART_FORM_DATA))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(product), response);
    }

    @Test
    public void getAllProducts() throws Exception {
        List<Product> products = Arrays.asList(this.getMockProduct());

        when(productRepository.findAll()).thenReturn(products);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(products), response);
    }

    private Product getMockProduct() {
        Product product = new Product();
        product.setName("Osmi Povjerenik");
        product.setAuthor("Renato Baretic");
        product.setPrice(10.99);
        product.setDescription("Good");
        product.setLanguage(new Language(1L, "Croatian"));
        product.setSellerId("auth0|12345");
        product.setCategoryId(345L);
        return product;
    }
}

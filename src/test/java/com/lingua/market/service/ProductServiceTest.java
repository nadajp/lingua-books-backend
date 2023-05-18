package com.lingua.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.lingua.market.model.Product;
import com.lingua.market.model.ProductDto;
import com.lingua.market.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AmazonS3 amazonS3;

    private ModelMapper modelMapper;

    private ProductService productService;

    private final String imageUrl = "http://test-image-url.com/test.jpg";

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        productService = new ProductService(productRepository, amazonS3, modelMapper);
    }

    @Test
    @DisplayName("Test createProduct method")
    public void testCreateProduct() throws IOException {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setDescription("Test Product Description");
        productDto.setPrice(10.0);

        byte[] fileBytes = new byte[] { 1, 2, 3 };
        InputStream is = new ByteArrayInputStream(fileBytes);
        MockMultipartFile imageFile = new MockMultipartFile("test.jpg", is);

        Product product = modelMapper.map(productDto, Product.class);
        product.setId(12345678L);

        when(productRepository.save(any())).thenReturn(product);
        when(amazonS3.getUrl(any(), any())).thenReturn(new java.net.URL(imageUrl));

        // When
        ProductDto createdProduct = productService.createProduct(productDto, imageFile);

        // Then
        assertEquals(productDto.getName(), createdProduct.getName());
        assertEquals(productDto.getDescription(), createdProduct.getDescription());
        assertEquals(productDto.getPrice(), createdProduct.getPrice());
        assertEquals(imageUrl, createdProduct.getImageUrl());
    }

}


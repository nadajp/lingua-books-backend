package com.lingua.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import com.amazonaws.services.s3.AmazonS3;
import com.lingua.market.web.dto.ProductDTO;
import com.lingua.market.persistence.dao.LanguageRepository;
import com.lingua.market.persistence.dao.ProductRepository;
import com.lingua.market.persistence.dao.SellerRepository;
import com.lingua.market.persistence.model.Language;
import com.lingua.market.persistence.model.Product;
import com.lingua.market.persistence.model.Seller;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private AmazonS3 amazonS3;

    @Mock
    private static UUID uuid;

    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    private final String imageUrl = "http://test-image-url.com/test.jpg";
    private final String LANGUAGE = "Croatian";

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        productService = new ProductService(productRepository, languageRepository, sellerRepository,
                        amazonS3, modelMapper);
    }

    @Test
    @DisplayName("Test createProduct method")
    public void testCreateProduct() throws IOException {
        ProductDTO productDto = new ProductDTO();
        productDto.setName("Test Product");
        productDto.setDescription("Test Product Description");
        productDto.setPrice(10.0);
        productDto.setLanguageId(1L);

        byte[] fileBytes = new byte[] { 1, 2, 3 };
        InputStream is = new ByteArrayInputStream(fileBytes);
        MockMultipartFile imageFile = new MockMultipartFile(imageUrl, is);

        Product product = modelMapper.map(productDto, Product.class);
        product.setId(12345678L);

        Language language = new Language();
        language.setId(1L);
        language.setName(LANGUAGE);
        Optional<Language> returnedLanguage = Optional.of(language);

        Seller seller = new Seller();
        seller.setAuthUser("auth|12345");
        seller.setDisplayName("Ana Banana");

        when(languageRepository.findById(any())).thenReturn(returnedLanguage);
        when(sellerRepository.findByAuthUser(any())).thenReturn(Optional.of(seller));
        when(productRepository.save(any())).thenReturn(product);

        UUID mockUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

        MockedStatic<UUID> mocked = Mockito.mockStatic(UUID.class);
        mocked.when(UUID::randomUUID).thenReturn(mockUUID);

        String expectedImageUrl = "images/" + mockUUID + "-" + imageFile.getOriginalFilename();

        ProductDTO createdProduct = productService.createProduct(productDto, imageFile);

        assertEquals(productDto.getName(), createdProduct.getName());
        assertEquals(productDto.getDescription(), createdProduct.getDescription());
        assertEquals(productDto.getPrice(), createdProduct.getPrice());
        assertEquals(expectedImageUrl, createdProduct.getImageUrl());
        assertEquals(productDto.getLanguageId(), createdProduct.getLanguageId());
        assertEquals(seller.getDisplayName(), createdProduct.getSellerName());
    }

}


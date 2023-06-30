package com.lingua.market.service;

import java.io.IOException;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.lingua.market.persistence.dao.LanguageRepository;
import com.lingua.market.persistence.dao.ProductRepository;
import com.lingua.market.persistence.model.Language;
import com.lingua.market.persistence.model.Product;
import com.lingua.market.web.dto.ProductDTO;
import com.lingua.market.web.exception.ResourceNotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final LanguageRepository languageRepository;
    
    private final AmazonS3 amazonS3;
    
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, 
                          LanguageRepository languageRepository, 
                          AmazonS3 amazonS3, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.languageRepository = languageRepository;
        this.amazonS3 = amazonS3;
        this.modelMapper = modelMapper;
    }

    public ProductDTO createProduct(ProductDTO productDto, MultipartFile imageFile) throws IOException {
        Product product = modelMapper.map(productDto, Product.class);

        Language language = languageRepository.findById(productDto.getLanguage().getId())
                .orElseThrow(() -> 
                new ResourceNotFoundException("Language not found for this id :: " + productDto.getLanguage().getId()));
    
        product.setLanguage(language);
        product = productRepository.save(product);

        if (imageFile != null) {
            try {             
                String imageUrl = uploadImage(imageFile);
                product.setImageUrl(imageUrl);
            } catch (IOException e) {
                // Handle any exceptions that occur during image upload
                System.err.println("Error uploading image: " + e.getMessage());
                // Optionally, you can rethrow the exception or handle it differently
                throw new RuntimeException("Error uploading image", e);
            }
            product = productRepository.save(product);
        } 
       
        return modelMapper.map(product, ProductDTO.class);
    }

    private String uploadImage(MultipartFile imageFile) throws IOException {
        // Generate a unique filename for the image
        String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();

        String bucketName = "lingua-books-images";
        String key = "images/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageFile.getContentType());
        amazonS3.putObject(bucketName, key, imageFile.getInputStream(), metadata);

        return key;
    }
}


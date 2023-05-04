package com.lingua.market.service;

import java.io.IOException;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.lingua.market.model.Product;
import com.lingua.market.model.ProductDto;
import com.lingua.market.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    //private final AmazonS3 amazonS3Service;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        //this.amazonS3Service = amazonS3Service;
        this.modelMapper = modelMapper;
    }

    public ProductDto createProduct(ProductDto productDto, MultipartFile imageFile) throws IOException {
        Product product = modelMapper.map(productDto, Product.class);
        // Save the product entity to the database
        product = productRepository.save(product);

        // Upload the product image to S3
        //String imageUrl = amazonS3Service.uploadFile(productDto.getImageUrl(), product.getId().toString());
        String imageUrl = uploadImage(imageFile);

        // Update the product entity with the image URL and save to the database
        product.setImageUrl(imageUrl);
        product = productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

    private String uploadImage(MultipartFile imageFile) throws IOException {
        // Generate a unique filename for the image
        String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();

        // Set the S3 bucket and key for the image
        String bucketName = "your-s3-bucket-name";
        String key = "images/" + fileName;

        // Upload the image file to S3
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setContentType(imageFile.getContentType());
        // s3Client.putObject(bucketName, key, imageFile.getInputStream(), metadata);

        // // Generate the URL for the uploaded image
        // String url = s3Client.getUrl(bucketName, key).toString();

        //return url;
        return "";
    }
}


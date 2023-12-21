package com.lingua.market.web.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lingua.market.web.dto.ProductDTO;
import com.lingua.market.persistence.dao.ProductRepository;
import com.lingua.market.persistence.model.Product;
import com.lingua.market.service.ProductService;
import com.lingua.market.web.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }    

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }    

    @GetMapping("/subcategory/{subcategoryId}")
    public List<Product> getProductBySubcategoryId(@PathVariable(value = "subcategoryId") Long subcategoryId) {
        return productRepository.findBySubcategoryId(subcategoryId);
    }    

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProductDTO> createProduct (@RequestPart("product") @Validated ProductDTO productDto,
                                                     @RequestPart(name = "image", required = false) MultipartFile imageFile) {
        
        ProductDTO createdProduct = null;
        try {
            createdProduct = productService.createProduct(productDto, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(URI.create("/products/" + createdProduct.getId()))
        .body(createdProduct);
    }
}

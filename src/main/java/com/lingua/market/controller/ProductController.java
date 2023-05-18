package com.lingua.market.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lingua.market.exception.ResourceNotFoundException;
import com.lingua.market.model.Product;
import com.lingua.market.model.ProductDto;
import com.lingua.market.repository.ProductRepository;
import com.lingua.market.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    private ProductRepository productRepository;

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
        System.out.println("CATEGORY ID: " + categoryId);
        return productRepository.findByCategoryId(categoryId);
    }    

    @GetMapping("/subcategory/{subcategoryId}")
    public List<Product> getProductBySubcategoryId(@PathVariable(value = "subcategoryId") Long subcategoryId) {
        return productRepository.findBySubcategoryId(subcategoryId);
    }    

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProductDto> createProduct (@RequestPart("product") @Validated ProductDto productDto,
                                                     @RequestPart(name = "image", required = false) MultipartFile imageFile) {
        
        ProductDto createdProduct = null;
        try {
            createdProduct = productService.createProduct(productDto, imageFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Return a response with the saved product
        return ResponseEntity.created(URI.create("/products/" + createdProduct.getId()))
        .body(createdProduct);
    }

}

package com.lingua.market.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lingua.market.model.Category;
import com.lingua.market.model.CategoryDTO;
import com.lingua.market.model.SubcategoryDTO;
import com.lingua.market.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/v1/categories")
    public List<CategoryDTO> getAllCategoriesWithSubcategories() {
        return categoryService.getAllCategoriesWithSubcategories();
    }

    @GetMapping("/api/v1/categories/{slug}")
    public Long getCategoryIdBySlug(@PathVariable(value = "slug") String slug) {
        return categoryService.getCategoryBySlug(slug);
    }

    @GetMapping("/api/v1/subcategories/{slug}")
    public Long getSubcategoryIdBySlug(@PathVariable(value = "slug") String slug) {
        return categoryService.getSubcategoryBySlug(slug);
    }

    @PostMapping(value="/api/v1/categories", consumes = {"application/json"}) 
    public ResponseEntity<CategoryDTO> createCategory (@Validated @RequestBody Category category) {
            CategoryDTO categoryDTO = categoryService.createCategory(category.getName(),
            category.getSlug());
            
            return ResponseEntity.created(URI.create("/categories/" + categoryDTO.getId()))
            .body(categoryDTO);
    }

    @PostMapping(value="/api/v1/subcategories", consumes = {"application/json"}) 
    public ResponseEntity<SubcategoryDTO> createSubCategory(
        @Validated @RequestBody SubcategoryDTO subcategoryDTO) {
        SubcategoryDTO createdSubcategoryDTO = categoryService.createSubcategory
                        (subcategoryDTO.getName(), 
                        subcategoryDTO.getSlug(),
                         subcategoryDTO.getParentCategoryId());
        
        return ResponseEntity.created(URI.create("/subcategories/" + createdSubcategoryDTO.getId()))
            .body(createdSubcategoryDTO);
    }
}


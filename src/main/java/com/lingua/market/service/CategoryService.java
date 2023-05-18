package com.lingua.market.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingua.market.model.Category;
import com.lingua.market.model.CategoryDTO;
import com.lingua.market.model.Subcategory;
import com.lingua.market.model.SubcategoryDTO;
import com.lingua.market.repository.CategoryRepository;
import com.lingua.market.repository.SubcategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Long getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug).getId();
    }

    public Long getSubcategoryBySlug(String slug) {
        return subcategoryRepository.findBySlug(slug).getId();
    }

    public SubcategoryDTO createSubcategory(String subcategoryName, String slug, Long parentId) {
        Category category = categoryRepository.
                            findById(parentId).
                            orElseThrow(() -> new RuntimeException("Parent category not found"));

        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryName);
        subcategory.setSlug(slug);
        subcategory.setParent(category);

        Subcategory createdSubcategory = subcategoryRepository.save(subcategory);

        return convertToSubcategoryDTO(createdSubcategory);
    }

    public CategoryDTO createCategory(String categoryName, String slug) {
        Category category = new Category();
        category.setName(categoryName);
        category.setSlug(slug);

        Category createdCategory = categoryRepository.save(category);
        
        return convertToCategoryDTO(createdCategory);
    }

    public List<CategoryDTO> getAllCategoriesWithSubcategories() {
        List<Category> categories = categoryRepository.findAll();
    
        return categories.stream()
            .peek(category -> category.setSubcategories(subcategoryRepository.findByParent(category)))
            .map(this::convertToCategoryDTO)
            .collect(Collectors.toList());
    }    

    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setSlug(category.getSlug());
    
        List<Subcategory> subcategories = category.getSubcategories();
        if (subcategories != null) {
            List<SubcategoryDTO> subcategoryDTOs = category.getSubcategories().stream()
            .map(this::convertToSubcategoryDTO)
            .collect(Collectors.toList());
            categoryDTO.setSubcategories(subcategoryDTOs);
        }
    
        return categoryDTO;
    }

    private SubcategoryDTO convertToSubcategoryDTO(Subcategory subcategory) {
        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        subcategoryDTO.setId(subcategory.getId());
        subcategoryDTO.setName(subcategory.getName());
        subcategoryDTO.setSlug(subcategory.getSlug());
        subcategoryDTO.setParentCategoryId(subcategory.getParent().getId());
    
        return subcategoryDTO;
    }
}

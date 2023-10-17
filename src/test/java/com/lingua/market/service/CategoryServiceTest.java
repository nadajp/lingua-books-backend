package com.lingua.market.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lingua.market.persistence.dao.CategoryRepository;
import com.lingua.market.persistence.dao.SubcategoryRepository;
import com.lingua.market.persistence.model.Category;
import com.lingua.market.persistence.model.Subcategory;
import com.lingua.market.web.dto.CategoryDTO;
import com.lingua.market.web.dto.SubcategoryDTO;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private SubcategoryRepository subcategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryService(subcategoryRepository, categoryRepository);
    }

    @Test
    public void testGetCategoryBySlug() {
        Category category = new Category();
        category.setId(1L);
        category.setSlug("test-slug");
        when(categoryRepository.findBySlug(anyString())).thenReturn(category);

        Long categoryId = categoryService.getCategoryBySlug("test-slug");

        assertThat(categoryId).isEqualTo(1L);
    }

    @Test
    public void testGetSubcategoryBySlug() {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(1L);
        subcategory.setSlug("test-slug");
        when(subcategoryRepository.findBySlug(anyString())).thenReturn(subcategory);

        Long subcategoryId = categoryService.getSubcategoryBySlug("test-slug");

        assertThat(subcategoryId).isEqualTo(1L);
    }

    @Test
    public void testCreateSubcategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("test-category");
        category.setSlug("test-slug");
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        Subcategory subcategory = new Subcategory();
        subcategory.setId(1L);
        subcategory.setName("test-subcategory");
        subcategory.setSlug("test-slug");
        subcategory.setParent(category);
        when(subcategoryRepository.save(any(Subcategory.class))).thenReturn(subcategory);

        SubcategoryDTO subcategoryDTO = categoryService.createSubcategory("test-subcategory", "test-slug", 1L);

        assertThat(subcategoryDTO.getId()).isEqualTo(1L);
        assertThat(subcategoryDTO.getName()).isEqualTo("test-subcategory");
        assertThat(subcategoryDTO.getSlug()).isEqualTo("test-slug");
        assertThat(subcategoryDTO.getParentCategoryId()).isEqualTo(1L);
    }

    @Test
    public void testCreateSubcategoryWithInvalidParent() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.createSubcategory("test-subcategory", "test-slug", 1L))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Parent category not found");
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("test-category");
        category.setSlug("test-slug");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.createCategory("test-category", "test-slug");

        assertThat(categoryDTO.getId()).isEqualTo(1L);
        assertThat(categoryDTO.getName()).isEqualTo("test-category");
        assertThat(categoryDTO.getSlug()).isEqualTo("test-slug");
    }

    @Test
    public void testGetAllCategoriesWithSubcategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("test-category-1");
        category1.setSlug("test-slug-1");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("test-category-2");
        category2.setSlug("test-slug-2");

        Subcategory subcategory1 = new Subcategory();
        subcategory1.setId(1L);
        subcategory1.setName("test-subcategory-1");
        subcategory1.setSlug("test-slug-1");
        subcategory1.setParent(category1);

        Subcategory subcategory2 = new Subcategory();
        subcategory2.setId(2L);
        subcategory2.setName("test-subcategory-2");
        subcategory2.setSlug("test-slug-2");
        subcategory2.setParent(category1);

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);
        when(subcategoryRepository.findByParent(category1)).thenReturn(List.of(subcategory1, subcategory2));
        when(subcategoryRepository.findByParent(category2)).thenReturn(new ArrayList<>());

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategoriesWithSubcategories();

        assertThat(categoryDTOs.size()).isEqualTo(2);

        CategoryDTO categoryDTO1 = categoryDTOs.get(0);
        assertThat(categoryDTO1.getId()).isEqualTo(1L);
        assertThat(categoryDTO1.getName()).isEqualTo("test-category-1");
        assertThat(categoryDTO1.getSlug()).isEqualTo("test-slug-1");

        List<SubcategoryDTO> subcategoryDTOs1 = categoryDTO1.getSubcategories();
        assertThat(subcategoryDTOs1.size()).isEqualTo(2);

        SubcategoryDTO subcategoryDTO1 = subcategoryDTOs1.get(0);
        assertThat(subcategoryDTO1.getId()).isEqualTo(1L);
        assertThat(subcategoryDTO1.getName()).isEqualTo("test-subcategory-1");
        assertThat(subcategoryDTO1.getSlug()).isEqualTo("test-slug-1");
        assertThat(subcategoryDTO1.getParentCategoryId()).isEqualTo(1L);

        SubcategoryDTO subcategoryDTO2 = subcategoryDTOs1.get(1);
        assertThat(subcategoryDTO2.getId()).isEqualTo(2L);
        assertThat(subcategoryDTO2.getName()).isEqualTo("test-subcategory-2");
        assertThat(subcategoryDTO2.getSlug()).isEqualTo("test-slug-2");
        assertThat(subcategoryDTO2.getParentCategoryId()).isEqualTo(1L);

        CategoryDTO categoryDTO2 = categoryDTOs.get(1);
        assertThat(categoryDTO2.getId()).isEqualTo(2L);
        assertThat(categoryDTO2.getName()).isEqualTo("test-category-2");
        assertThat(categoryDTO2.getSlug()).isEqualTo("test-slug-2");

        List<SubcategoryDTO> subcategoryDTOs2 = categoryDTO2.getSubcategories();
        assertThat(subcategoryDTOs2.size()).isEqualTo(0);
    }
}
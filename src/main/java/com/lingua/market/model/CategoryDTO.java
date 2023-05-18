package com.lingua.market.model;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private String slug;

    private List<SubcategoryDTO> subcategories;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getSlug() {
        return slug;
    }
    public void setSubcategories(List<SubcategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }
    public List<SubcategoryDTO> getSubcategories() {
        return subcategories;
    }
}


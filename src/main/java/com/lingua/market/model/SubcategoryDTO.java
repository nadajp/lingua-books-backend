package com.lingua.market.model;

public class SubcategoryDTO {
    private Long id;
    private String name;
    private String slug;
    private Long parentCategoryId;

    // Add getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getParentCategoryId() {
        return parentCategoryId;
    }
    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getSlug() {
        return slug;
    }
}


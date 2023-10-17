package com.lingua.market.persistence.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Subcategory> subcategories;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    
    public List<Subcategory> getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}

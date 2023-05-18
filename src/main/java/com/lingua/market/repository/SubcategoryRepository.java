package com.lingua.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.model.Category;
import com.lingua.market.model.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findByParent(Category parent);
    Subcategory findBySlug(String slug);
}
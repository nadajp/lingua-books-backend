package com.lingua.market.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.persistence.model.Category;
import com.lingua.market.persistence.model.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findByParent(Category parent);
    <Optional> Subcategory findBySlug(String slug);
}
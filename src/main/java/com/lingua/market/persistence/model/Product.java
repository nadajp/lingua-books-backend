package com.lingua.market.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String author;
    
    @Column(length = 2048)
    private String description;
    
    @ManyToOne
    private Language language;

    private String imageUrl;
    
    private String publisher;
    
    @Column(name = "publication_year")
    private String publicationYear;
    
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "subcategory_id")
    private Long subcategoryId;
    
    private String isbn;
    
    private Double price;
    
    private String condition;
    
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "seller_name")
    private String sellerName;
   
    @Column(name = "number_of_pages")
    private String numberOfPages;
    
    private String format;
    
    @Column(name = "dimensions_height")
    private String dimensionsLength;

    @Column(name = "dimensions_width")
    private String dimensionsWidth;

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return author;
    }
    public void setTitle(String title) {
        this.author = title;
    }
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSellerId() {
        return sellerId;
    }
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String seller) {
        this.sellerName = seller;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String title) {
        this.author = title;
    }
    public String getNumberOfPages() {
        return numberOfPages;
    }
    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getDimensionsLength() {
        return dimensionsLength;
    }
    public void setDimensionsLength(String dimensionsLength) {
        this.dimensionsLength = dimensionsLength;
    }
    public String getDimensionsWidth() {
        return dimensionsWidth;
    }
    public void setDimensionsWidth(String dimensionsWidth) {
        this.dimensionsWidth = dimensionsWidth;
    }
    public String getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getSubcategoryId() {
        return subcategoryId;
    }
    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String image) {
        this.imageUrl = image;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
}

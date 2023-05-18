package com.lingua.market.model;

public class ProductDto {
    private Long id;
    private String name;
    private String author;
    private double price;
    private String condition;
    private Language language;
    private String description;
    private Long categoryId;
    private Long subcategoryId;
    private String publisher;
    private String publicationYear;
    private String isbn;
    private String length;
    private String format;
    private String dimensionLength;
    private String dimensionWidth;
    private String imageUrl;
    private Long sellerId;

    // Constructors
    public ProductDto() {}
    
    public ProductDto(String name, String author, double price, String condition, 
    Language language, Long categoryId, Long subcategoryId, Long sellerId) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.condition = condition;
        this.language = language;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.sellerId = sellerId;
    }

    // Getters and Setters
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Long getSellerId() {
        return sellerId;
    }
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String title) {
        this.author = title;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String numberOfPages) {
        this.length = numberOfPages;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getDimensionLength() {
        return dimensionLength;
    }
    public void setDimensionLength(String dimensionsLength) {
        this.dimensionLength = dimensionsLength;
    }
    public String getDimensionWidth() {
        return dimensionWidth;
    }
    public void setDimensionWidth(String dimensionsWidth) {
        this.dimensionWidth = dimensionsWidth;
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

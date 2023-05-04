package com.lingua.market.model;

public class ProductDto {
    private Long id;
    private String name;
    private String author;
    private String price;
    private String condition;
    private String language;
    private String description;
    private String categoryId;
    private String subcategoryId;
    private String publisher;
    private String publicationYear;
    private String isbn;
    private String length;
    private String format;
    private String dimensionLength;
    private String dimensionWidth;
    private String imageUrl;
    private String sellerId;

    // Constructors
    public ProductDto() {}

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
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSellerId() {
        return sellerId;
    }
    public void setSellerId(String sellerId) {
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
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getSubcategoryId() {
        return subcategoryId;
    }
    public void setSubcategoryId(String subcategoryId) {
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

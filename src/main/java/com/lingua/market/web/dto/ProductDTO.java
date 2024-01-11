package com.lingua.market.web.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private String author;
    private Double price;
    private String condition;
    private Long languageId;
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
    private String sellerId;
    private String sellerName;

    public ProductDTO() {}
    
    public ProductDTO(String name, String author, Double price, String condition, 
    Long languageId, Long categoryId, Long subcategoryId, String sellerId) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.condition = condition;
        this.languageId = languageId;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long productId) {
        this.id = productId;
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
    public Long getLanguageId() {
        return this.languageId;
    }
    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
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
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}

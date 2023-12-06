package com.temp.webshop.webshop.entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(unique = true)
    private String productName;
    private int cost;
    private String description;

    public Product() {}

    public Product(String productName, int cost, String description) {
        this.productName = productName;
        this.cost = cost;
        this.description = description;
    }

    public Product(Long productId, String productName, int cost, String description) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }
}

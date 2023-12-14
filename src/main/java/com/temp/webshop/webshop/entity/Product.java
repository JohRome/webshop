package com.temp.webshop.webshop.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String name;
    private double price;
    private String description;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts = new ArrayList<>();

    public long getProductId() {
        return productId;
    }

    public void setProductId(long id) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

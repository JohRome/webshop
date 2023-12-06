package com.temp.webshop.webshop.entity;

import jakarta.persistence.Entity;

import java.util.Set;

@Entity
public class ShoppingCart {

    private Long cartId;
    Set<Product> products;

    public ShoppingCart(Long cartId, Set<Product> products) {
        this.cartId = cartId;
        this.products = products;
    }

    public ShoppingCart(Set<Product> products) {
        this.products = products;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

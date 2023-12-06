package com.temp.webshop.webshop.entity;

import com.temp.webshop.authentication.entity.ApplicationUser;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long cartId;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;

    public ShoppingCart(Long cartId, List<Product> products) {
        this.cartId = cartId;
        this.products = products;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

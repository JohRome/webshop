package com.temp.webshop.webshop.entity;

import com.temp.webshop.authentication.entity.Customer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    /***
     * En shoppingcart till en user
     */
    @OneToOne(mappedBy = "shoppingCart")
    private Customer user;

    /***
     * en shoppingcart för flera produkter
     */
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public ShoppingCart() {}

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
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

    public void addProduct(Product product) {
        this.products.add(product);
        product.setShoppingCart(this);
    }

    // Add a method to remove a product from the list
    public void removeProduct(Product product) {
        this.products.remove(product);
        product.setShoppingCart(null);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}

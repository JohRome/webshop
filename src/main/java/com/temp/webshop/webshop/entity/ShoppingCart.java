package com.temp.webshop.webshop.entity;

import com.temp.webshop.authentication.entity.User;
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
    private User user;

    /***
     * en shoppingcart för flera produkter
     */
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Article> products;

    public ShoppingCart() {
        setProducts(this.products = new ArrayList<>());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart(List<Article> products) {
        this.products = products;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<Article> getProducts() {
        return products;
    }

    public void setProducts(List<Article> products) {
        this.products = products;
    }

    /*public void addProduct(Product product) {
        this.products.add(product);
        product.setShoppingCart(this);
    }

    // Add a method to remove a product from the list
    public void removeProduct(Product product) {
        this.products.remove(product);
        product.setShoppingCart(null);
    }*/

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}

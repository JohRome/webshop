package com.temp.webshop.authentication.entity;

import com.temp.webshop.webshop.entity.ShoppingCart;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;

    //private String jwtToken;// ---> som alltid hänger med och autentiserar User (user.getJwtToken());

    /***
     * En user till en shoppingcart
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "cartId")
    private ShoppingCart shoppingCart;

    /***
     * En user kan ha flera roller
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;

    public ApplicationUser() {}

    public ApplicationUser(String username, String password, ShoppingCart shoppingCart) {
        this.username = username;
        this.password = password;
        this.shoppingCart = shoppingCart;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}

package com.temp.webshop.authentication.entity;

import com.temp.webshop.webshop.entity.ShoppingCart;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;

    //private JwtToken jwtToken; ---> som alltid hänger med och autentiserar User (user.getJwtToken());
    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    public ApplicationUser() {}

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
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
        shoppingCart.setUser(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

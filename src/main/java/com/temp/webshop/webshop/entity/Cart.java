package com.temp.webshop.webshop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.temp.webshop.auth.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    /**
     * Johan kommenterade bort detta block.
     *
     */
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private Customer customer;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    //@JsonManagedReference(value = "cart-backref")
    private List<CartItem> cartItems = new ArrayList<>();

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    @MapKey(name = "product")
//    private Map<Product, CartItem> cartItems = new HashMap<>();

}
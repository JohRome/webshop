package com.temp.webshop.webshop.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    //@JsonBackReference(value = "product-backref")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    //@JsonBackReference(value = "cart-backref")
    private Cart cart;

    private int quantity;



}
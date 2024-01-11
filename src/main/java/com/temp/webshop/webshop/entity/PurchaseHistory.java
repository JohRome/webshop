package com.temp.webshop.webshop.entity;

import com.temp.webshop.auth.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="history_id")
    private Long id;

    @Column(unique = true)
    private String history;

    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    public PurchaseHistory(Long id, String history, double price) {
//
//    }
}
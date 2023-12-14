package com.temp.webshop.webshop.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Plats i databasen där köparens tidigare köp ligger.
 *
 * Metoder för att se tidigare köp i HistoryService (måste autentiseras med nuvarande user så att user inte kommer åt
 * andras tidigare köp)
 * Endpoints för att hämta och se i HistoryController
 */

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Cart> earlierCarts;
    public History (){}
    public History(List<Cart> earlierShoppingCarts) {
        this.earlierCarts = earlierCarts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Cart> getEarlierCarts() {
        return earlierCarts;
    }

    public void setEarlierShoppingCarts(List<Cart> earlierCarts) {
        this.earlierCarts = earlierCarts;
    }
}

package com.temp.webshop.webshop.entity;

import com.temp.webshop.webshop.entity.ShoppingCart;
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
    private int id;
    private List<ShoppingCart> earlierShoppingCarts;
    public History (){}
    public History(List<ShoppingCart> earlierShoppingCarts) {
        this.earlierShoppingCarts = earlierShoppingCarts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ShoppingCart> getEarlierShoppingCarts() {
        return earlierShoppingCarts;
    }

    public void setEarlierShoppingCarts(List<ShoppingCart> earlierShoppingCarts) {
        this.earlierShoppingCarts = earlierShoppingCarts;
    }
}

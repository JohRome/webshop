package com.temp.webshop.webshop.repository;

import com.temp.webshop.webshop.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}

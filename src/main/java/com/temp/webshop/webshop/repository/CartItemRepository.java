package com.temp.webshop.webshop.repository;

import com.temp.webshop.webshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
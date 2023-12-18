package com.temp.webshop.webshop.repository;

import com.temp.webshop.webshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartById(Long id);

//    Cart findCartByCustomerUsername(String username);

}
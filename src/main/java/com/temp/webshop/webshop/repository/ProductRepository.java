package com.temp.webshop.webshop.repository;

import com.temp.webshop.webshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
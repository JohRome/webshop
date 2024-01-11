package com.temp.webshop.auth.repository;

import com.temp.webshop.auth.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    Boolean existsByUsername(String username);

}
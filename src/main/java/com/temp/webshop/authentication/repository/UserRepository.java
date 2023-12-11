package com.temp.webshop.authentication.repository;


import com.temp.webshop.authentication.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customer, Long> {
}

package com.temp.webshop.auth.repository;

import com.temp.webshop.auth.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing users in the application.
 */
public interface UserRepository extends JpaRepository<Customer, Long> {

//    Optional<Customer> findByEmail(String email);

//    Optional<Customer> findByUsernameOrEmail(String username, String email);

    Optional<Customer> findByUsername(String username);

    Boolean existsByUsername(String username);

//    Boolean existsByEmail(String email);

}

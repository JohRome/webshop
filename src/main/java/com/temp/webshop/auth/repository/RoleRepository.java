package com.temp.webshop.auth.repository;

import com.temp.webshop.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing roles in the application.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);

    Boolean existsByName(String roleName);

}
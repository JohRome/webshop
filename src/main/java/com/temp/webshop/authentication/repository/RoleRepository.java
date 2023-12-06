package com.temp.webshop.authentication.repository;

import com.temp.webshop.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

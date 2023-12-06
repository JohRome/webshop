package com.temp.webshop.authentication.repository;


import com.temp.webshop.authentication.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
}

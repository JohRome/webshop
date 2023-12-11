package com.temp.webshop.auth.config;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.entity.Role;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * RoleInitializer's only purpose is to add the ROLE_GARDEN_MASTER to the MySQL Database
 */
@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminWithAdminRole() {
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            Customer admin = new Customer(
                    0L,
                    "admin",
                    passwordEncoder.encode("admin"),
                    roles);
            customerRepository.save(admin);
        }

    }

    @PostConstruct
    public void createRoleUser() {
        if (!roleRepository.existsByName("ROLE_USER")) {
            Role gardenMasterRole = new Role();
            gardenMasterRole.setName("ROLE_USER");
            roleRepository.save(gardenMasterRole);
        }
    }
}

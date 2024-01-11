package com.temp.webshop.auth.configuration;

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

@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method that creates a new user with an admin role. This simplifies the start-up.
     */
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

    /**
     * Method that creates a new User-role(customer).
     */
    @PostConstruct
    public void createRoleUser() {

        if (!roleRepository.existsByName("ROLE_USER")) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
    }
}

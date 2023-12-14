package com.temp.webshop.authentication.configuration;

import com.temp.webshop.authentication.entity.Role;
import com.temp.webshop.authentication.entity.User;;
import com.temp.webshop.authentication.repository.RoleRepository;
import com.temp.webshop.authentication.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RoleInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void createAdminWithAdminRole() {

        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(
                    0L,
                    "admin",
                    passwordEncoder.encode("admin"),
                    roles);
            userRepository.save(admin);
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

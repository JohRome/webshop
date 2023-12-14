package com.temp.webshop.authentication.configuration;

import com.temp.webshop.authentication.entity.Role;
import com.temp.webshop.authentication.entity.UserRole;
import com.temp.webshop.authentication.repository.RoleRepository;
import com.temp.webshop.webshop.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        createRoleIfNotExists(UserRole.ADMIN);
    }

    @PostConstruct
    public void createUserRole() {
        createRoleIfNotExists(UserRole.USER);
    }

    private void createRoleIfNotExists(UserRole userRole) {
        String roleName = "ROLE_" + userRole.name();
        if(!roleRepository.existsByName(roleName)) {
            Role role = new Role();
            role.setUserRole(userRole);
            roleRepository.save(role);
        }
    }
}

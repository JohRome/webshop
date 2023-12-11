package com.temp.webshop.auth.config;

import com.temp.webshop.auth.entity.Role;
import com.temp.webshop.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * RoleInitializer's only purpose is to add the ROLE_GARDEN_MASTER to the MySQL Database
 */
@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void createRoleGardenMaster() {
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role gardenMasterRole = new Role();
            gardenMasterRole.setName("ROLE_ADMIN");
            roleRepository.save(gardenMasterRole);
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

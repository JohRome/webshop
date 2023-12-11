package com.temp.webshop.auth.service;

import com.temp.webshop.auth.entity.Role;
import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.payload.LoginDTO;
import com.temp.webshop.auth.payload.RegisterDTO;
import com.temp.webshop.auth.repository.RoleRepository;
import com.temp.webshop.auth.repository.UserRepository;
import com.temp.webshop.auth.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    /**
     * Registers a new user with the provided information.
     *
     * @param dto The RegisterDTO containing user registration details.
     * @return A success message upon successful registration.
     * @throws HttpClientErrorException If the username or email already exists.
     */
    @Override
    public String register(RegisterDTO dto) {

        if (!isUserOrEmailExisting(dto)) {

            var gardenMaster = createNewCustomer(dto);

            Set<Role> gardenMasterRole = setUserRole();
            gardenMaster.setRoles(gardenMasterRole);

            userRepository.save(gardenMaster);
        }

        return "Garden Master registered successfully!";
    }

    private boolean isUserOrEmailExisting(RegisterDTO dto) { // Måste ändras namn på

        if (userRepository.existsByUsername(dto.getUsername())) {
            System.out.println("Username already exists!"); // Måste ändras
        }

//        if (userRepository.existsByEmail(dto.getEmail()))
//            System.out.println("Email already exists!"); // Måste ändras

        return false;
    }

    public Set<Role> setUserRole() {

        Set<Role> roles = new HashSet<>();
        // The ROLE_USER is automatically set to a newly registered customer by default
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        return roles;
    }

    private Customer createNewCustomer(RegisterDTO dto) {

        var customer = new Customer();
//        customer.setName(dto.getName());
        customer.setUsername(dto.getUsername());
//        customer.setEmail(dto.getEmail());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        return customer;
    }

    /**
     * Performs user login and generates a JWT token upon successful authentication.
     *
     * @param dto The LoginDTO containing user credentials.
     * @return The generated JWT token.
     */
    @Override
    public String login(LoginDTO dto) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }
}

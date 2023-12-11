package com.temp.webshop.auth.service;

import com.temp.webshop.auth.entity.Role;
import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.payload.LoginDTO;
import com.temp.webshop.auth.payload.RegisterDTO;
import com.temp.webshop.auth.repository.RoleRepository;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.auth.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;


    private boolean isUsernameExisting(RegisterDTO dto) {

        // Vi kan bygga våra egna Exceptions så det hanteras snyggare ist för att använda en fjutting sout
        if (customerRepository.existsByUsername(dto.getUsername())) {
            System.out.println("Username already exists!");
        }

        return false;
    }


    private Set<Role> setUserRole() {

        // Här bara sätter vi ROLE_USER till automatiskt till varje Customer som reggar sig
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        return roles;
    }

    private Customer createNewCustomer(RegisterDTO dto) {

        var customer = new Customer();
        customer.setUsername(dto.getUsername());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        return customer;
    }

    @Override
    public String register(RegisterDTO dto) {

        if (!isUsernameExisting(dto)) {

            var gardenMaster = createNewCustomer(dto);

            Set<Role> gardenMasterRole = setUserRole();
            gardenMaster.setRoles(gardenMasterRole);

            customerRepository.save(gardenMaster);
        }

        // Samma sak här, vi kanske kan returna något roligare än en fattig String, om det ens behövs...
        return "Customer registered successfully!";
    }

    @Override
    public String login(LoginDTO dto) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }
}

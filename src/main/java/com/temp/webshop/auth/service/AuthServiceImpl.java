package com.temp.webshop.auth.service;

import com.temp.webshop.auth.entity.Role;
import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.payload.LoginDTO;
import com.temp.webshop.auth.payload.RegisterDTO;
import com.temp.webshop.auth.repository.RoleRepository;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.auth.security.JWTTokenProvider;
import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.repository.CartRepository;
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

    private final CartRepository cartRepository;


    private boolean isUsernameExisting(RegisterDTO dto) {

        if (customerRepository.existsByUsername(dto.getUsername())) {
            System.out.println("Username already exists!");
        }

        return false;
    }


    private Set<Role> setUserRole() {

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        return roles;
    }

    private Customer createNewCustomer(RegisterDTO dto) {
        var cart = createNewCart();

        var customer = new Customer();
        customer.setUsername(dto.getUsername());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setCart(cart);

        return customer;
    }
    private Cart createNewCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public String register(RegisterDTO dto) {

        if (!isUsernameExisting(dto)) {

            var customer = createNewCustomer(dto);

            Set<Role> userRole = setUserRole();
            customer.setRoles(userRole);

            customerRepository.save(customer);
        }
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
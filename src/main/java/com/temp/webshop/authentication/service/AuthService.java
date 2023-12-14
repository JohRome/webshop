package com.temp.webshop.authentication.service;

import com.temp.webshop.authentication.Payload.LoginDTO;
import com.temp.webshop.authentication.Payload.RegisterDTO;
import com.temp.webshop.authentication.entity.Role;
import com.temp.webshop.authentication.entity.User;
import com.temp.webshop.authentication.repository.RoleRepository;
import com.temp.webshop.authentication.security.JWTTokenProvider;
import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;
    private final CartRepository cartRepository;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                       JWTTokenProvider jwtTokenProvider, CartRepository cartRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.cartRepository = cartRepository;
    }

    private boolean isUsernameExisting(RegisterDTO dto) {
        if(userRepository.existsByUsername(dto.getUsername())) {
            System.out.println("Username is already taken");
        }
        return false;
    }

    private Set<Role> setUserRole() {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        return roles;
    }

    private Cart createNewCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }

    private User createNewUser(RegisterDTO dto) {
        var cart = createNewCart();
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCart(cart);

        return user;
    }

    @Override
    public String login(LoginDTO dto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }

    @Override
    public String register(RegisterDTO dto) {
        if(!isUsernameExisting(dto)) {
            var user = createNewUser(dto);

            Set<Role> userRole = setUserRole();
            user.setRoles(userRole);

            userRepository.save(user);
        }
        return "Registration completed";
    }
}

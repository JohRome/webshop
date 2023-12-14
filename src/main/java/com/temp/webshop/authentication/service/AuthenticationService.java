package com.temp.webshop.authentication.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    /*private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final ShoppingCartRepository shoppingCartRepository;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                                 TokenService tokenService, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * 1. Kunden registrerar sig
     * 2. Kunden får automatiskt tilldelat en kundvagn --> customer.getShoppingCart();
     * 3. Kunden kan därefter utföra authentiserad CRUD genom att vi i en metod hämtar dess nuvarande kundvagn..
     * ---> ShoppingCart shoppingCart = user.getShoppingCart();
     * ---> Eftersom att ShoppingCart innehåller en lista av produkter kan man lägga till produkt genom,.
     * ---> shoppingCart.addProduct(product);
     *
     * 4. Kunden checkar ut sin kundvagn
     * ---> shoppingCartRepository.save(user.getShoppingCart());
     * 5. Nuvarande ShoppingCart töms när shoppingsessionen avslutas
     * ---> shoppingCart.deleteAll();
     * ---> System.exit(0);
     * @param username
     * @param password
     * @return
     */



    /*public User registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return new User(username, encodedPassword);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }*/
}

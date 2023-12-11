package com.temp.webshop.authentication.service;

import com.temp.webshop.authentication.entity.Customer;
import com.temp.webshop.authentication.entity.Role;
import com.temp.webshop.authentication.repository.RoleRepository;
import com.temp.webshop.authentication.repository.UserRepository;
import com.temp.webshop.webshop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /*@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;*/

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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



    public Customer registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

    }

    public loginUser(String username, String password) {

    }
}

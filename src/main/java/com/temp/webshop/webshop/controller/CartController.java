package com.temp.webshop.webshop.controller;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.webshop.dto.CartItemDTO;
import com.temp.webshop.webshop.dto.ProductQuantityToCartDTO;
import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.entity.CartItem;
import com.temp.webshop.webshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addProductToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody ProductQuantityToCartDTO quantity) {
        cartService.addProductToCart(userDetails.getUsername(), id, quantity.getQuantity());
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> getAllProductsFromCart(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        ResponseEntity<String> response = cartService.getAllProductsFromCart(userDetails.getUsername());

        return response;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void removeProductFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody ProductQuantityToCartDTO quantity) {
        cartService.removeProductFromCart(userDetails.getUsername(), id, quantity.getQuantity());
    }
}
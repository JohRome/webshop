package com.temp.webshop.webshop.controller;

import com.temp.webshop.webshop.dto.ProductQuantityToCartDTO;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        cartService.addProductToCart(userDetails, id , quantity.getQuantity());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void removeProductFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody ProductQuantityToCartDTO quantity) {
        cartService.removeProductFromCart(userDetails, id, quantity.getQuantity());
    }

}
package com.temp.webshop.webshop.controller;

import com.temp.webshop.webshop.dto.ProductQuantityToCartDTO;
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

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addProductToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId,
            @RequestBody ProductQuantityToCartDTO quantity) {
        cartService.addProductToCart(userDetails, productId, quantity.getQuantity());
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void removeProductFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId,
            @RequestBody ProductQuantityToCartDTO quantity) {
        cartService.removeProductFromCart(userDetails, productId, quantity.getQuantity());
    }
}

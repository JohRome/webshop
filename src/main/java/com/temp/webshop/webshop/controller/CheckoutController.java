package com.temp.webshop.webshop.controller;

import com.temp.webshop.webshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webshop/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CartService cartService;

    @DeleteMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> checkoutCart(
            @AuthenticationPrincipal UserDetails userDetails
            ) {

        String response = cartService.getReceipt(userDetails.getUsername());
        cartService.emptyCart(userDetails.getUsername());

        return ResponseEntity.ok(response);
    }
}
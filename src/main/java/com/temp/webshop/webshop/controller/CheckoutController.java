package com.temp.webshop.webshop.controller;

import com.temp.webshop.webshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CartService cartService;


    /*public ResponseEntity<String> checkoutCart(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String receipt = cartService.getReceipt(userDetails);

        cartService.emptyCart(userDetails);

        return ResponseEntity.ok(receipt);
    }
}*/
    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> checkoutCart(
            @AuthenticationPrincipal UserDetails userDetails
            ) {

        ResponseEntity<String> response = cartService.getReceipt(userDetails);

        return response;
    }
}

/*ResponseEntity<String> response = cartService.getAllProductsFromCart(userDetails);

        String endShopping = "Thank you for your payment! Here's your receipt: \n" + response;*/
//cartService.deleteCart(userDetails);


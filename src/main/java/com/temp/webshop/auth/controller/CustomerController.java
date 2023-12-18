package com.temp.webshop.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/")
    // Denna annoteringen är den jag hänvisar till i SecurityConfig klassen. Båda sätt fungerar så vi får välja
    // vad vi vill köra på
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCustomerName(@AuthenticationPrincipal UserDetails userDetails) {
        // Det är här som jag hoppas att magin kan hända. Via userDetails får vi username och med det
        // kan vi väl banne mig hitta Cart?
        return String.format("You are logged in as %s ", userDetails.getUsername());
    }
}

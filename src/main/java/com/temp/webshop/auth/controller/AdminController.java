package com.temp.webshop.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String getAdminName(@AuthenticationPrincipal UserDetails userDetails) {
        return String.format("You are logged in as %s", userDetails.getUsername());
    }
}

package com.temp.webshop.authentication.controller;

import com.temp.webshop.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    /*@PostMapping("/register")
    public Customer registerUser(
            @RequestBody RegistrationDTO body
            ) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());

    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(
            @RequestBody RegistrationDTO body
    ) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }*/
}

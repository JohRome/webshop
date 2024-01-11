package com.temp.webshop.auth.controller;

import com.temp.webshop.auth.payload.JWTAuthResponse;
import com.temp.webshop.auth.payload.LoginDTO;
import com.temp.webshop.auth.payload.RegisterDTO;
import com.temp.webshop.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO dto) {

        String token = authService.login(dto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {

        String response = authService.register(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
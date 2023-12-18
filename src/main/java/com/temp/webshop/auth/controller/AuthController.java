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

        // Vi kan välja att skicka tillbaka något annat än JWTAuthResponse ifall vi vill visa flera detaljer,
        // så som användarnamn kanske? Cart?
        String token = authService.login(dto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {

        // Samma som ovan, vi kanske ska skicka tillbaka något mer än en fjuttig String?
        String response = authService.register(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
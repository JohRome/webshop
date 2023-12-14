package com.temp.webshop.authentication.controller;

import com.temp.webshop.authentication.Payload.JWTAuthResponse;
import com.temp.webshop.authentication.Payload.LoginDTO;
import com.temp.webshop.authentication.Payload.RegisterDTO;
import com.temp.webshop.authentication.entity.LoginResponseDTO;
import com.temp.webshop.authentication.entity.RegistrationDTO;
import com.temp.webshop.authentication.entity.User;
import com.temp.webshop.authentication.service.AuthService;
import com.temp.webshop.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterDTO dto
    ) {
        String response = authService.register(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(
            @RequestBody LoginDTO dto
    ) {
        String token = authService.login(dto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
}

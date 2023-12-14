package com.temp.webshop.authentication.Payload;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    /*private User user;
    private String jwtToken;*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

/**
 * Exakt samma innehåll som LoginDTO
 * -varför?
 */

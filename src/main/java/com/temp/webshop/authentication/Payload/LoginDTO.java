package com.temp.webshop.authentication.Payload;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}

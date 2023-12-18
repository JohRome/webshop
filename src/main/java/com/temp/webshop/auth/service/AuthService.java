package com.temp.webshop.auth.service;

import com.temp.webshop.auth.payload.LoginDTO;
import com.temp.webshop.auth.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO dto);
    String register(RegisterDTO dto);
}
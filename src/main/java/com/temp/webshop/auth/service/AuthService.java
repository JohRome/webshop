package com.temp.webshop.auth.service;

import com.temp.webshop.auth.payload.LoginDTO;
import com.temp.webshop.auth.payload.RegisterDTO;

/**
 * Service interface for authentication-related operations.
 */
public interface AuthService {
    String login(LoginDTO dto);
    String register(RegisterDTO dto);
}

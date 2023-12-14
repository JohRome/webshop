package com.temp.webshop.authentication.service;

import com.temp.webshop.authentication.Payload.LoginDTO;
import com.temp.webshop.authentication.Payload.RegisterDTO;

public interface AuthServiceInterface {
    String login(LoginDTO dto);
    String register(RegisterDTO dto);
}

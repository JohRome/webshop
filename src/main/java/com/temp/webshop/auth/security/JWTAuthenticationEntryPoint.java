package com.temp.webshop.auth.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Component handling unauthorized access attempts, providing a standardized response.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Invoked when an unauthenticated user attempts to access a secured resource.
     *
     * @param request       The HTTP request made by the user.
     * @param response      The HTTP response that will be sent to the user.
     * @param authException The authentication exception that triggered the entry point.
     * @throws IOException      If an input or output exception occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // If a user tries to make an unauthorized request, an exception is thrown
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

    }
}

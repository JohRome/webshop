package com.temp.webshop.authentication.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * AuthenticationEntryPoint hanterar autentiseringsrelaterade fel - speciellt när en oautentiserad användare försöker
 * nå en säkrad punkt.
 * Har en metod "commence" - tar in en HttpServletRequest, HttpServletReponse och en AuthenticationException.
 * - Har ansvar för att starta autentiseringsprocessen och omdirigera användaren till en inloggningssida
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) {
        try {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

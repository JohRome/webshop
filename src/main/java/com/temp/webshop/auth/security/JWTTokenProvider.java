package com.temp.webshop.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.security.Key;
import java.util.Date;

/**
 * Component responsible for generating, parsing, and validating JWT tokens.
 */
@Component
public class JWTTokenProvider {

    // @Value is used to get the secret key from the application.properties file
    @Value("${app.jwt-secret-key}")
    private String jwtSecretKey;

    // Same as above...
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;


    /**
     * Generates a JWT token based on the provided authentication information.
     *
     * @param authentication The authentication object representing the user.
     * @return The generated JWT token.
     */
    public String generateJwtToken(Authentication authentication) {
        // The username or email that represents the authenticated user in the Authentication object
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        // Builds the JWT-Token with the provided values and then returns it
        return Jwts.builder()
                // Setting who's the JWT-Token is for, in this case, our registered admin
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                // The decoded secret key is needed to sign every new JWT-Token
                .signWith(key())
                // Serialize the JWT-Token to a String
                .compact();
    }


    /**
     * Returns a decoded secret key object from the application.properties file.
     *
     * @return The decoded secret key.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecretKey)
        );
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param jwtToken The JWT token.
     * @return The username extracted from the token.
     */
    public String getUsername(String jwtToken) {

        // Claims represents the user data
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Validates the provided JWT token.
     *
     * @param jwtToken The JWT token to validate.
     * @return True if the token is valid, false otherwise.
     * @throws HttpStatusCodeException If the token is invalid, a Bad Request status is thrown.
     */
    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(jwtToken);
            return true;
        } catch (JwtException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST, "Check your token, because it's faulty") {
            };
        }
    }
}

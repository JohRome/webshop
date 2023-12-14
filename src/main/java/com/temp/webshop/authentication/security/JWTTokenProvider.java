package com.temp.webshop.authentication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.security.Key;
import java.util.Date;


@Component
public class JWTTokenProvider {
    @Value("${app.jwt-secret-key}")
    private String jwtSecretKey;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;


    public String GenerateJwtToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        //Vart sätter vi jwtExpirationDate???
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecretKey)
        );
    }

    public String getUsername(String jwtToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parse(jwtToken);
            return true;
        } catch (JwtException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST, "Check your token, because it's faulty") {
            };
        }
    }
}

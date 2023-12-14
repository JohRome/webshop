package com.temp.webshop.authentication.Payload;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthResponse {
    private String accessToken;
    private String TokenType = "Bearer";

    *

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }
}

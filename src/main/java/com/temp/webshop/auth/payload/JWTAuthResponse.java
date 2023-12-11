package com.temp.webshop.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {

    // Vi kanske kan flytta ut hela klassen till en separat modul. På så sätt kan Client-sidan få tillgång till
    // alla nödvändiga fält, om det ens behövs. Det är inte så jobbigt att fixa senare iofs...
    private String accessToken;
    private String tokenType = "Bearer";

}

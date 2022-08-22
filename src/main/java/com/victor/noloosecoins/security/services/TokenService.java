package com.victor.noloosecoins.security.services;

import com.victor.noloosecoins.security.models.Credentials;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;


public class TokenService {
    @Value("${security.token.expiration}")
    private String expiration;
    @Value("${security.token.secret}")
    private String secret;
    @Value("${security.token.issuer}")
    private String issuer;

    public String generateToken(Authentication authentication){
        Credentials creds = (Credentials) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(creds.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public boolean isTokenValid(String token){
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | IllegalArgumentException | SignatureException | MalformedJwtException |
                 UnsupportedJwtException e) {
            return false;
        }
    }

    public Long getLoggedUser(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}

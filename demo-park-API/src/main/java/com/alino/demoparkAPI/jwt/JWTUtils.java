package com.alino.demoparkAPI.jwt;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTUtils {
    
    public static final String JWT_BEARER = "Bearer";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "123456-123456-123456-123456-1234567";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 2;

    private JWTUtils(){}

    private static javax.crypto.SecretKey generateKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return (Date) Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JWTToken creaToken(String usuario, String cargo){

        Date issueAt = new Date(EXPIRE_DAYS);
        Date limit = toExpireDate(issueAt);

        String token = Jwts
                       .builder()
                       .header().add("typ", "JWT")
                       .and()
                       .subject(usuario)
                       .issuedAt(issueAt)
                       .expiration(limit)
                       .signWith(generateKey())
                       .claim("cargo", cargo)
                       .compact();

        return new JWTToken(token);
    }

    private static Claims getClainmsFromToken(String token){
        try {
            return Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(refactorToken(token)).getPayload();
        } catch (JwtException exception) {
            log.error(String.format("Token inválido %s", exception.getMessage()));
        }
        return null;
    }

    public static String getUsernameFromToken(String token){
        return getClainmsFromToken(token).getSubject();
    }

    private static String refactorToken(String token){
        if (token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        } 
        return token;
    }

    public static boolean isTokenValidS(String token){
        try {
            Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(refactorToken(token));
            return true;
        } catch (JwtException exception) {
            log.error(String.format("Token inválido %s", exception.getMessage()));
        }
        return false;
    }
}

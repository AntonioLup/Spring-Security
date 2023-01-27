package com.secured.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

@SuppressWarnings("deprecation")
public class TokenUtil {

    private final static String ACCES_TOKEN_SECRET = "91k5D@A20W&72t!FOKcaGEaV*91k5D@A20W&72t!FOKcaGEaV*91k5D@A20W&72t!FOKcaGEaV*91k5D@A20W&72t!FOKcaGEaV*";
    private final static Long ACCES_TOKEN_VALID_SECONDS = 2_593_000L;

    public static String createToken(String nombre, String email){
        long expirate = ACCES_TOKEN_VALID_SECONDS * 1_000;
        Date expirateDate = new Date(System.currentTimeMillis() + expirate);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirateDate)
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS384  , ACCES_TOKEN_SECRET.getBytes())
                .compact();
    }
    public static UsernamePasswordAuthenticationToken getAuth(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey( ACCES_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());

        }catch (JwtException e){
            return null;
        }
    }
}

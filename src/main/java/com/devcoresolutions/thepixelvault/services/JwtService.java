package com.devcoresolutions.thepixelvault.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    //con esta notación @Value obtenemos el valor de la variable declarada en el archivo application.properties
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long expiration;

    //Este método es para generar el token
    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                //fijamos la fecha en que se generó el token
                .issuedAt(new Date(System.currentTimeMillis()))
                //fijamos el tiempo en que va a expirar el token
                .expiration(new Date(System.currentTimeMillis() + expiration))
                //rimamos el token con la llave secreta
                .signWith(getSignKey(), Jwts.SIG.HS256)
                //compactamos para que nos devuelva la cadena
                .compact();
    }

    //Este método extreae el email de nuestro token.
    public String extractEmail(String token){
        return getClaims(token).getSubject();
    }

    //Verifica si un token es valido
    public boolean isValidToken(String token, String email){
        final String emailtoken = extractEmail(token);
        boolean isExpired = getClaims(token).getExpiration().before(new Date());
        return (emailtoken.equals(email)) && !isExpired;
    }

    //Este método lo que esta haciendo es extraer los claim de los json webtokens
    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //obtenemos la llave secreta que viene en el token
    private SecretKey getSignKey(){
        //decodificamos la llave y la guardamos en un array de bytes
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
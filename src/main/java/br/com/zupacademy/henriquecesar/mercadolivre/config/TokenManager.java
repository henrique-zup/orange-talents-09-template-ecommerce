package br.com.zupacademy.henriquecesar.mercadolivre.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenManager {
    
    @Value("${api.mercadolivre.jwt.expiration}")
    private String expirationTime;
    
    @Value("${api.mercadolivre.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        UsuarioLogado usuario = (UsuarioLogado) authentication.getPrincipal();
        Date dateNow = new Date();
        Date dateExpiration = new Date(dateNow.getTime() + Long.parseLong(expirationTime));
        
        return Jwts.builder()
                .setIssuer("API Orange Talents")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(dateNow)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret)
            .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}

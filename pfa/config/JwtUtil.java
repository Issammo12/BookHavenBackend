package org.example.pfa.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.pfa.dao.entities.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtil {
    private final String SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    public String generateToken(User user) {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getIdUser())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(secretKey)
                .compact();

    }
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

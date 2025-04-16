package br.com.fiap.apisecurity.security;


import br.com.fiap.apisecurity.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API FIAP") // Issuer of the token
                    .withSubject(user.getUsername()) // Subject of the token
                    .withExpiresAt(genExpirationInstant()) // Token expiration time (1 hour)
                    .sign(algorithm); // Sign the token with the algorithm

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }


        }
    private Instant genExpirationInstant() {
        return LocalDateTime.now().plusMinutes(2).toInstant(ZoneOffset.UTC); // Token expiration time (2 minutes)
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API FIAP")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}



package springboot.projetfinal.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Base64;

@Service
public class JwtService {
    // Utilisation d'une clé sans caractères spéciaux
    private static final String SECRET_KEY = "une_cle_tres_secrete_tres_longue_qui_doit_etre_stockee_secure_123456789";
    
    // Encodage de la clé en Base64 pour plus de sécurité
    private static final byte[] SIGNING_KEY = Base64.getEncoder().encode(SECRET_KEY.getBytes());

    public String generateToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public String extractLogin(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails ud) {
        return extractLogin(token).equals(ud.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenBlacklistService {

    public void blacklist(String token, long durationInSeconds) {
        // No-op (ne fait rien)
    }

    public boolean isBlacklisted(String token) {
        return false; // aucun token n’est blacklisté
    }
}

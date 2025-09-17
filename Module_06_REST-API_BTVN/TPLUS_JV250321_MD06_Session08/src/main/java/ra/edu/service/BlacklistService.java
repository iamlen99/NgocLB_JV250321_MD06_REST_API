package ra.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.BlacklistedToken;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Roles;
import ra.edu.repository.BlacklistedTokenRepository;
import ra.edu.security.jwt.JwtProvider;

import java.util.Date;

public interface BlacklistService {
    void blacklistToken(String token);
    boolean isBlacklisted(String token);
    void removeExpiredTokens();
}


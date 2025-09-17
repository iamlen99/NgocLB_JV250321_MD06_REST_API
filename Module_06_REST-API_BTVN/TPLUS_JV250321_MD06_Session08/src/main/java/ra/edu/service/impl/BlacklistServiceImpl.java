package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.BlacklistedToken;
import ra.edu.repository.BlacklistedTokenRepository;
import ra.edu.security.jwt.JwtProvider;
import ra.edu.service.BlacklistService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {
    private final BlacklistedTokenRepository blacklistedTokenRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void blacklistToken(String token) {
        Date expiry = jwtProvider.getExpirationDateFromToken(token);
        blacklistedTokenRepository.save(
                BlacklistedToken.builder()
                        .token(token)
                        .expiryDate(expiry)
                        .build()
        );
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklistedTokenRepository.existsByToken(token);
    }

    // Optional: cron job để dọn dẹp token đã hết hạn
    @Override
    @Scheduled(cron = "0 0 * * * *") // mỗi giờ
    public void removeExpiredTokens() {
        Date now = new Date();
        blacklistedTokenRepository.deleteAll(
                blacklistedTokenRepository.findAll().stream()
                        .filter(t -> t.getExpiryDate().before(now))
                        .toList()
        );
    }
}

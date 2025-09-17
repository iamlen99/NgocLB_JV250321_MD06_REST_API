package ra.edu.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.edu.security.principal.UserPrincipal;

import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.expire}")
    private Long EXPIRED;
    @Value("${jwt.refresh}")
    private Long REFRESH;
    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    public String generateToken(UserPrincipal userPrincipal) {
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", userPrincipal.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRED))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Failed -> Expired Token Message {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Failed -> Unsupported Token Message {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Failed -> Invalid Format Token Message {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Failed -> Invalid Signature Token Message {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Failed -> Claims Empty Token Message {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String refreshToken(String jwtOld) {
        String username = getUsernameFromToken(jwtOld); // Lấy subject từ token cũ
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now.getTime() + REFRESH))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}

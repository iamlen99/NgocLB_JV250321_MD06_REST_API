package ra.edu.securirty.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ra.edu.securirty.principal.UserPrincipal;

import java.util.Date;
import java.util.stream.Collectors;

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
                .claim("roles", userPrincipal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet())
                )
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Failed -> Expired Token Message {}", e.getMessage());
            throw new JwtException("Expired Token Message");
        } catch (UnsupportedJwtException e) {
            log.error("Failed -> Unsupported Token Message {}", e.getMessage());
            throw new JwtException("Unsupported Token Message");
        } catch (MalformedJwtException e) {
            log.error("Failed -> Invalid Format Token Message {}", e.getMessage());
            throw new JwtException("Invalid Format Token Message");
        } catch (SignatureException e) {
            log.error("Failed -> Invalid Signature Token Message {}", e.getMessage());
            throw new JwtException("Invalid Signature Token Message");
        } catch (IllegalArgumentException e) {
            log.error("Failed -> Claims Empty Token Message {}", e.getMessage());
            throw new JwtException("Claims Empty Token Message");
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

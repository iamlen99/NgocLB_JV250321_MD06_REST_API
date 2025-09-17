package ra.edu.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ra.edu.security.principal.UserDetailServiceCustom;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailServiceCustom userDetailServiceCustom;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/v1/auths/") || path.startsWith("/v1/public/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getTokenFromRequest(request);

        try {
            if (token == null) {
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "MISSING_TOKEN", "Authorization token is required.");
                return;
            }

            if (!jwtProvider.validateToken(token)) {
                log.warn("Invalid JWT signature for token: {}", token);
                sendError(response, HttpServletResponse.SC_FORBIDDEN, "INVALID_SIGNATURE", "Invalid token signature.");
                return;
            }

            String username = jwtProvider.getUsernameFromToken(token);
            if (username != null) {
                UserDetails userDetails = userDetailServiceCustom.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "TOKEN_EXPIRED", "JWT has expired. Please login again.");
        } catch (io.jsonwebtoken.SignatureException e) {
            sendError(response, HttpServletResponse.SC_FORBIDDEN,
                    "INVALID_SIGNATURE", "Invalid token signature.");
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST,
                    "MALFORMED_TOKEN", "Token format is invalid.");
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            sendError(response, HttpServletResponse.SC_FORBIDDEN,
                    "UNSUPPORTED_TOKEN", "Token algorithm or key not supported.");
        } catch (Exception e) {
            log.error("JWT Authentication error: {}", e.getMessage());
            sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "JWT_ERROR", "Unexpected error while processing token.");
        }
    }

    private void sendError(HttpServletResponse response, int status, String error, String message) throws IOException {
        if (response.isCommitted()) return; // tránh ghi đè khi response đã trả
        response.resetBuffer();
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(
                String.format("{\"error\":\"%s\", \"message\":\"%s\"}", error, message)
        );
        response.getWriter().flush(); // đảm bảo ghi xong trước khi kết thúc
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

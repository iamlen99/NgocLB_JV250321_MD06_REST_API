package ra.edu.securirty.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.securirty.principal.UserDetailServiceCustom;
import ra.edu.securirty.principal.UserPrincipal;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailServiceCustom userDetailServiceCustom;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        try {
            String token = getTokenFromRequest(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                if (username != null) {
                    UserPrincipal userDetails = (UserPrincipal) userDetailServiceCustom.loadUserByUsername(username);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (JwtException e) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        ApiDataResponse<Object> errorResponse = ApiDataResponse.error(e.getMessage());
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

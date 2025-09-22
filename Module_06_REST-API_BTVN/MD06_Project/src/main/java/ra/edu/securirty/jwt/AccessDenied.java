package ra.edu.securirty.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class AccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        log.error("Un Permission {}", accessDeniedException.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write(
                "{\"success\":false,\"message\":\"Bạn không có quyền truy cập\",\"errors\":[\"Forbidden\"],\"timestamp\":\""
                        + LocalDateTime.now() + "\"}"
        );
    }
}

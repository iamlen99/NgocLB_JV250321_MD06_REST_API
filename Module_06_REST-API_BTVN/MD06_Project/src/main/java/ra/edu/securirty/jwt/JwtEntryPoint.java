package ra.edu.securirty.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ra.edu.model.response.ApiDataResponse;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("Unauthorized error: {}", authException.getMessage());

        String message = "Bạn chưa đăng nhập";
        List<String> errors = List.of("Unauthorized");

        if (authException.getMessage() != null) {
            if (authException.getMessage().contains("Invalid")) {
                message = "Token không hợp lệ";
                errors = List.of("InvalidToken");
            } else if (authException.getMessage().contains("expired")) {
                message = "Token đã hết hạn";
                errors = List.of("ExpiredToken");
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiDataResponse<Object> apiResponse = ApiDataResponse.error(errors, message);
        objectMapper.writeValue(response.getWriter(), apiResponse);
    }

    @PostConstruct
    public void checkModules() {
        System.out.println("ObjectMapper modules: " + objectMapper.getRegisteredModuleIds());
    }
}



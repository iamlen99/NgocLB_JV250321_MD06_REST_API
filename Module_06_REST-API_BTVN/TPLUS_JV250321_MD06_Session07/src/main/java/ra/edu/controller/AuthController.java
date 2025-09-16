package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.UserPrincipal;
import ra.edu.model.request.LoginRequest;
import ra.edu.model.request.RegisterRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.model.response.UserResponse;
import ra.edu.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiDataResponse<String>> login(@RequestBody LoginRequest request) {
        ApiDataResponse<String> response = new ApiDataResponse<>();
        try {
            // Dùng AuthenticationManager để xác thực
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Nếu xác thực thành công → lưu Authentication vào SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            response.setSuccess(true);
            response.setMessage("Login successful!");
            response.setData("Welcome " + request.getUsername());
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            response.setSuccess(false);
            response.setMessage("Invalid username or password");
            response.setErrors(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiDataResponse<UserResponse>> register(@RequestBody RegisterRequest request) {
        ApiDataResponse<UserResponse> response = new ApiDataResponse<>();
        try {
            UserResponse userResponse = authService.register(request);
            response.setSuccess(true);
            response.setMessage("User registered successfully!");
            response.setData(userResponse);
            response.setErrors(null);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            response.setSuccess(false);
            response.setMessage("Registration failed");
            response.setData(null);
            response.setErrors(ex.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(response);
        }
    }
}

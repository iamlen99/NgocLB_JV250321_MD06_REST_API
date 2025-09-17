package ra.edu.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.entity.Users;
import ra.edu.model.request.RefreshTokenRequest;
import ra.edu.model.request.UserLogin;
import ra.edu.model.request.UserRegister;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.model.response.JwtResponse;
import ra.edu.service.BlacklistService;
import ra.edu.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auths")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlacklistService blacklistService;

    @PostMapping("/register")
    public ResponseEntity<ApiDataResponse<Users>> registerUser(@RequestBody UserRegister userRegister) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Register user successfully!",
                userService.register(userRegister),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiDataResponse<JwtResponse>> login(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Login successfully!",
                userService.login(userLogin),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiDataResponse<JwtResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtResponse response = userService.refreshAccessToken(refreshTokenRequest.getRefreshToken());

        return ResponseEntity.ok(new ApiDataResponse<>(
                true,
                "New access token generated",
                response,
                null,
                HttpStatus.OK
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiDataResponse<String>> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(
                    new ApiDataResponse<>(
                            false,
                            "Missing token",
                            null, null,
                            HttpStatus.BAD_REQUEST
                    ));
        }
        String token = header.substring(7);
        blacklistService.blacklistToken(token);

        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Logout successful. Token revoked.",
                        null,
                        null,
                        HttpStatus.OK
                ));
    }
}

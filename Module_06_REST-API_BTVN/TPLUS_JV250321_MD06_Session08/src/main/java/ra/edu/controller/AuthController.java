package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.entity.Users;
import ra.edu.model.request.UserLogin;
import ra.edu.model.request.UserRegister;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.model.response.JwtResponse;
import ra.edu.service.UserService;

@RestController
@RequestMapping("/v1/auths")
public class AuthController {
    @Autowired
    private UserService userService;

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
}

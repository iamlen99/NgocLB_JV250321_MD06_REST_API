package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.UserRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.model.response.UserResponse;
import ra.edu.service.CinemaService;
import ra.edu.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiDataResponse<UserResponse>> register(@Valid @RequestBody UserRequest request) {
        UserResponse createdUser = userService.register(request);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Registered user successfully!",
                createdUser,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<UserResponse>>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<UserResponse> users = userService.getAllUsers(page, size);

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Retrieved users successfully!",
                users,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}


package ra.edu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Movie;
import ra.edu.model.entity.User;
import ra.edu.model.request.MovieRequest;
import ra.edu.model.request.UserRequest;
import ra.edu.model.response.UserResponse;
import ra.edu.repository.MovieRepository;
import ra.edu.repository.UserRepository;
import ra.edu.service.MovieService;
import ra.edu.service.UserService;
import ra.edu.storage.CloudinaryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse register(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Register duplicated username: {}", request.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Register duplicated email: {}", request.getEmail());
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole() != null ? request.getRole() : User.Role.USER)
                .build();

        User savedUser = userRepository.save(user);
        log.info("Register user: {}", savedUser.getUsername());

        return toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers(int page, int size) {
        long start = System.currentTimeMillis();
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));

        if (userPage.isEmpty()) {
            log.error("Failed to retrieve users: No users found for page={}, size={}", page, size);
            throw new NoSuchElementException("No users found for this page");
        }

        List<UserResponse> responseList = userPage.getContent()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        long duration = System.currentTimeMillis() - start;
        log.info("Retrieved {} users for page={}, size={}, time={}ms", responseList.size(), page, size, duration);

        return responseList;
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}

package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.InvalidCredentialsException;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Roles;
import ra.edu.model.entity.Users;
import ra.edu.model.request.UserLogin;
import ra.edu.model.request.UserRegister;
import ra.edu.model.response.JwtResponse;
import ra.edu.repository.RoleRepository;
import ra.edu.repository.UserRepository;
import ra.edu.securirty.jwt.JwtProvider;
import ra.edu.securirty.principal.UserPrincipal;
import ra.edu.service.AuthService;
import ra.edu.service.UserService;
import ra.edu.validation.UserValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;
    private final UserValidator userValidator;

    @Override
    public Users register(UserRegister userRegister) {
        userValidator.validateUserInfo(userRegister);

        if (userRegister.getRoles() != null
                && !userRegister.getRoles().isEmpty()
                && !userRegister.getRoles().equals(Set.of(RoleName.STUDENT.name()))) {
            throw new AccessDeniedException("Bạn chỉ có thể tạo tài khoản STUDENT");
        }

        Roles role = roleRepository.findByRoleName(RoleName.STUDENT)
                .orElseThrow(() -> new NoSuchElementException("Không tồn tại role STUDENT"));

        Users users = Users.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .fullName(userRegister.getFullName())
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .isActive(true)
                .roles(Set.of(role))
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(users);
    }

    @Override
    public Users getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findById(userPrincipal.getUserId());
    }

    @Override
    public JwtResponse login(UserLogin userLogin) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        } catch (AuthenticationException e) {
            log.error("Sai username hoac password: {}", e.getMessage());
            throw new InvalidCredentialsException("Tài khoản hoặc mật khẩu không chính xác");
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String generatedToken = jwtProvider.generateToken(userPrincipal);

        return JwtResponse.builder()
                .username(userPrincipal.getUsername())
                .fullName(userPrincipal.getFullName())
                .email(userPrincipal.getEmail())
                .phone(userPrincipal.getPhone())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .isActive(true)
                .createdAt(userPrincipal.getCreatedAt())
                .updatedAt(userPrincipal.getUpdatedAt())
                .token(generatedToken)
                .typeAuthentication("Bearer")
                .build();
    }
}

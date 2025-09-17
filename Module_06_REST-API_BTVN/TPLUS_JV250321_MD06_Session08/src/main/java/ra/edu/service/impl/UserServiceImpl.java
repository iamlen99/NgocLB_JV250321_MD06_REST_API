package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Roles;
import ra.edu.model.entity.Users;
import ra.edu.model.request.UserLogin;
import ra.edu.model.request.UserRegister;
import ra.edu.model.response.JwtResponse;
import ra.edu.repository.UserRepository;
import ra.edu.security.jwt.JwtProvider;
import ra.edu.security.principal.UserPrincipal;
import ra.edu.service.RoleService;
import ra.edu.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public Users register(UserRegister userRegister) {
        Set<Roles> roles = new HashSet<>();
        if (userRegister.getRoles() == null || userRegister.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.USER));
        } else {
            userRegister.getRoles().forEach(
                    role -> {
                        switch (role) {
                            case "ADMIN":
                                roles.add(roleService.findByRoleName(RoleName.ADMIN));
                                break;
                            case "EDITOR":
                                roles.add(roleService.findByRoleName(RoleName.EDITOR));
                            case "USER":
                                roles.add(roleService.findByRoleName(RoleName.USER));
                                break;
                            default:
                                throw new RuntimeException("role not found");
                        }
                    }
            );
        }
        Users users = Users.builder()
                .username(userRegister.getUsername())
                .fullName(userRegister.getFullName())
                .email(userRegister.getEmail())
                .address(userRegister.getAddress())
                .phone(userRegister.getPhone())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .roles(roles)
                .build();
        return userRepository.save(users);
    }

    @Override
    public JwtResponse login(UserLogin userLogin) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Username or Password is incorrect");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return JwtResponse.builder()
                .username(userPrincipal.getUsername())
                .fullName(userPrincipal.getFullName())
                .email(userPrincipal.getEmail())
                .address(userPrincipal.getAddress())
                .phone(userPrincipal.getPhone())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .status(userPrincipal.getStatus())
                .accessToken(jwtProvider.generateAccessToken(userPrincipal))
                .refreshToken(jwtProvider.generateRefreshToken(userPrincipal))
                .build();
    }

    @Override
    public JwtResponse refreshAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }
        String username = jwtProvider.getUsernameFromToken(refreshToken);
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserPrincipal userPrincipal = (UserPrincipal) UserPrincipal.getUserDetails(user);

        return JwtResponse.builder()
                .username(userPrincipal.getUsername())
                .fullName(userPrincipal.getFullName())
                .email(userPrincipal.getEmail())
                .address(userPrincipal.getAddress())
                .phone(userPrincipal.getPhone())
                .roles(userPrincipal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet()))
                .status(userPrincipal.getStatus())
                .accessToken(jwtProvider.generateAccessToken(userPrincipal))
                .refreshToken(refreshToken)
                .build();
    }
}

package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.BadRequestException;
import ra.edu.mapper.RoleMapper;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Users;
import ra.edu.model.request.RoleRequest;
import ra.edu.model.request.UserRegister;
import ra.edu.model.request.UserUpdate;
import ra.edu.repository.UserRepository;
import ra.edu.service.UserService;
import ra.edu.validation.UserValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final RoleMapper roleMapper;

    @Override
    public Page<Users> getAllUsers(int page, int size, String role) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        if (role == null) {
            return userRepository.findAll(pageable);
        }

        return switch (role) {
            case "STUDENT", "MENTOR", "ADMIN" ->
                    userRepository.findAllByRolesRoleName(RoleName.valueOf(role), pageable);
            default -> throw new NoSuchElementException("Không tìm thấy role " + role);
        };
    }

    @Override
    public Users createUser(UserRegister userRegister) {
        userValidator.validateUserInfo(userRegister);

        Users users = Users.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .fullName(userRegister.getFullName())
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .isActive(true)
                .roles(roleMapper.mapToSetRole(userRegister.getRoles()))
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return userRepository.save(users);
    }

    @Override
    public Users updateUser(Long userId, UserUpdate userUpdate) {
        Users existingUser = findById(userId);
        userValidator.validateUserInfo(existingUser, userUpdate);

        existingUser.setFullName(userUpdate.getFullName());
        existingUser.setEmail(userUpdate.getEmail());
        existingUser.setPhone(userUpdate.getPhone());
        existingUser.setUpdatedAt(LocalDateTime.now().withNano(0));
        return userRepository.save(existingUser);
    }

    @Override
    public Users updateUserStatus(Long userId, String status) {
        Users existingUser = findById(userId);
        if(status.equalsIgnoreCase("true")){
            existingUser.setIsActive(true);
        } else if(status.equalsIgnoreCase("false")){
            existingUser.setIsActive(false);
        }else {
            throw new BadRequestException("Tham số status chỉ được phép là true hoặc false");
        }
        return userRepository.save(existingUser);
    }

    @Override
    public Users updateUser(Long userId, RoleRequest roleRequest) {
        Users existingUser = findById(userId);
        if(existingUser.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ADMIN))) {
            throw new AccessDeniedException("Bạn không có quyền đổi role của ADMIN khác");
        }
        existingUser.setRoles(roleMapper.mapToSetRole(roleRequest.getRoles()));
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Users userDelete = findById(id);
        if(userDelete.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ADMIN))) {
            throw new AccessDeniedException("Bạn không có quyền xóa tài khoản của ADMIN khác");
        }
        userRepository.delete(userDelete);
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID không tồn tại"));
    }
}

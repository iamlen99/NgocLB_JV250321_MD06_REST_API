package ra.edu.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.Users;
import ra.edu.model.request.UserRegister;
import ra.edu.model.request.UserUpdate;
import ra.edu.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateUserInfo(UserRegister userRegister) {
        if (userRepository.existsByUsername(userRegister.getUsername())) {
            throw new ResourceConflictException("Tên người dùng đã tồn tại!");
        }
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new ResourceConflictException("Email đã tồn tại!");
        }

        if (userRepository.existsByPhone(userRegister.getPhone())) {
            throw new ResourceConflictException("Số điện thoại đã tồn tại!");
        }
    }

    public void validateUserInfo(Users existingUser, UserUpdate userUpdate) {
        if (!existingUser.getEmail().equals(userUpdate.getEmail())
        && userRepository.existsByEmail(userUpdate.getEmail())) {
            throw new ResourceConflictException("Email đã tồn tại!");
        }

        if (!existingUser.getPhone().equals(userUpdate.getPhone())
            && userRepository.existsByPhone(userUpdate.getPhone())) {
            throw new ResourceConflictException("Số điện thoại đã tồn tại!");
        }
    }
}

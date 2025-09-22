package ra.edu.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Users;
import ra.edu.model.request.UserRegister;
import ra.edu.model.request.UserUpdate;
import ra.edu.repository.UserRepository;

@Component
public class UserRoleValidator {
    public boolean isAdmin(Users user) {
        return user.getRoles()
                .stream()
                .anyMatch(role -> role.getRoleName() == RoleName.ADMIN);
    }

    public boolean isMentor(Users user) {
        return user.getRoles()
                .stream()
                .anyMatch(role -> role.getRoleName() == RoleName.MENTOR);
    }
}

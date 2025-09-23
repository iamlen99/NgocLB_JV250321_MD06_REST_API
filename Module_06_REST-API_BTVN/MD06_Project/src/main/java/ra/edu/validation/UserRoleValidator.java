package ra.edu.validation;

import org.springframework.stereotype.Component;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Users;

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

    public boolean isStudent(Users user) {
        return user.getRoles()
                .stream()
                .anyMatch(role -> role.getRoleName() == RoleName.STUDENT);
    }
}

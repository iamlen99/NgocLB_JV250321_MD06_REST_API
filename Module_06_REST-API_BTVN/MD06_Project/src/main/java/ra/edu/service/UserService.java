package ra.edu.service;

import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import ra.edu.model.entity.Users;
import ra.edu.model.request.RoleRequest;
import ra.edu.model.request.UserRegister;
import ra.edu.model.request.UserUpdate;

import java.util.Set;

public interface UserService {
    Page<Users> getAllUsers(int page, int size, String role);

    Users findById(Long id);

    Users createUser(UserRegister userRegister);

    Users updateUser(Long userId, UserUpdate userUpdate);

    Users updateUserStatus(Long userId, String status);

    Users updateUser(Long userId, RoleRequest roleRequest);

    void deleteUser(Long id);
}

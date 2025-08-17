package ra.edu.service;

import jakarta.servlet.http.HttpSession;
import ra.edu.model.dto.UserDTO;
import ra.edu.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO, HttpSession session);
    void deleteUser(User user);
}

package ra.edu.service;

import ra.edu.model.dto.UserDTO;
import ra.edu.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void addUser(UserDTO userDTO);
    void updateUser(User user);
    void deleteUser(User user);
}

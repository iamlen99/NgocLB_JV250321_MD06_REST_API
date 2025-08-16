package ra.edu.repository;

import ra.edu.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(User user);
}

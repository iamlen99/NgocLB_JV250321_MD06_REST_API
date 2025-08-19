package ra.edu.repository;

import ra.edu.model.entity.User;

public interface UserRepository {
    User login(String username, String password);
}

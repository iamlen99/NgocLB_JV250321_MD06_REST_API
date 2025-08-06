package ra.exercise.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.Data;
import ra.exercise.model.entity.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private Data data;
    public List<User> getAllUsers() {
        return data.getUsers();
    }
}

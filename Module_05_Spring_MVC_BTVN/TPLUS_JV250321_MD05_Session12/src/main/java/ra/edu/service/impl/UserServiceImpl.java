package ra.edu.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.dto.UserDTO;
import ra.edu.model.entity.User;
import ra.edu.repository.UserRepository;
import ra.edu.service.UserService;
import ra.edu.storage.CloudinaryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        try {
            user.setAvatar(cloudinaryService.uploadImage(userDTO.getImageFile()));
        } catch (IOException e) {
            System.out.println("Có lỗi trong quá trình upload ảnh:" + e.getMessage());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO, HttpSession session) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        if (userDTO.getImageFile() != null && !userDTO.getImageFile().isEmpty()) {
            try {
                user.setAvatar(cloudinaryService.uploadImage(userDTO.getImageFile()));
            } catch (IOException e) {
                System.out.println("Có lỗi trong quá trình upload ảnh:" + e.getMessage());
            }
        } else {
            User currentUser = (User) session.getAttribute("currentUser");
            user.setAvatar(currentUser.getAvatar());
        }
        userRepository.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

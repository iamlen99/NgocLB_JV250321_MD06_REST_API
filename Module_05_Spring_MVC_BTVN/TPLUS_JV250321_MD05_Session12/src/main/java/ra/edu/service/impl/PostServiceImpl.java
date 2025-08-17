package ra.edu.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.dto.PostDTO;
import ra.edu.model.entity.Post;
import ra.edu.model.entity.User;
import ra.edu.repository.PostRepository;
import ra.edu.service.PostService;
import ra.edu.storage.CloudinaryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Post> findAllPosts(Long userId) {
        return postRepository.findAll(userId);
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void addPost(PostDTO postDTO, HttpSession session) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        if (postDTO.getImageFile() != null && !postDTO.getImageFile().isEmpty()) {
            try {
                post.setImage(cloudinaryService.uploadImage(postDTO.getImageFile()));
            } catch (IOException e) {
                System.out.println("Có lỗi trong quá trình upload ảnh: " +  e.getMessage());
                post.setImage(null);
            }
        } else {
            post.setImage(null);
        }
        User currentUser = (User) session.getAttribute("currentUser");
        post.setUser(currentUser);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}

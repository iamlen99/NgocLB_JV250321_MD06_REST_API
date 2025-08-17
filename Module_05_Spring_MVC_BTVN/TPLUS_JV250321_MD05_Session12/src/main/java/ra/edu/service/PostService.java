package ra.edu.service;

import jakarta.servlet.http.HttpSession;
import ra.edu.model.dto.PostDTO;
import ra.edu.model.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAllPosts(Long id);
    Optional<Post> findPostById(Long id);
    void addPost(PostDTO postDTO, HttpSession session);
    void deletePost(Post post);
}

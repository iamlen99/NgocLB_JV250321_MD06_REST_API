package ra.edu.repository;

import ra.edu.model.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll(Long id);
    Optional<Post> findById(Long id);
    void save(Post post);
    void delete(Post post);
}

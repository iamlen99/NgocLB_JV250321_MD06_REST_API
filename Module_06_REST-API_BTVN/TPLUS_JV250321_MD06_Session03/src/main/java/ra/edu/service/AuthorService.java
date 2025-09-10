package ra.edu.service;

import ra.edu.model.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author createAuthor(Author author);

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);
}

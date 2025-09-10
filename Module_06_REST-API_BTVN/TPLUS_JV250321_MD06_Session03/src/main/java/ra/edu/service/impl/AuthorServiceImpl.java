package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Author;
import ra.edu.repository.AuthorRepository;
import ra.edu.service.AuthorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Author not found by id : " + id));
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author existingAuthor = getAuthorById(id);
        existingAuthor.setName(author.getName());
        existingAuthor.setCountry(author.getCountry());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}

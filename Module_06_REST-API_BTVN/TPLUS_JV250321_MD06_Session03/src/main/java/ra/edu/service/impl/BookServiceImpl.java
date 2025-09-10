package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Author;
import ra.edu.model.entity.Book;
import ra.edu.repository.AuthorRepository;
import ra.edu.repository.BookRepository;
import ra.edu.service.AuthorService;
import ra.edu.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(Long id) {
        authorService.getAuthorById(id);
        return bookRepository.findAllByAuthorId(id);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Book not found by id : " + id));
    }

    @Override
    public Book createBook(Book book, Long authorId) {
        Author author = authorService.getAuthorById(authorId);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book, Long authorId) {
        Book existingBook = getBookById(id);
        Author author = authorService.getAuthorById(authorId);
        existingBook.setAuthor(author);
        existingBook.setTitle(book.getTitle());
        existingBook.setQuantity(book.getQuantity());
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}

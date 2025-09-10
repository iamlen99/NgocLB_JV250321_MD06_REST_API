package ra.edu.service;

import ra.edu.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(Long id);

    Book getBookById(Long id);

    Book createBook(Book book, Long authorId);

    Book updateBook(Long id, Book book, Long authorId);

    void deleteBook(Long id);
}

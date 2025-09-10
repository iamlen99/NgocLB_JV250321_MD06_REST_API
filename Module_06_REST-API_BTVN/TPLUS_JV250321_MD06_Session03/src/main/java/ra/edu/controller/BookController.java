package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Book;
import ra.edu.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Book>>> getAllBooks() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Books successfully",
                bookService.getAllBooks(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Book>> insertBook(@RequestBody Book book) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new book successfully",
                bookService.createBook(book, book.getAuthor().getId()),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Book>> updateBook(@PathVariable Long id,@RequestBody Book book) {
        book.setId(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update book " + id + " successfully",
                bookService.updateBook(id, book,  book.getAuthor().getId()),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Delete book " + id + " successfully",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-author")
    public ResponseEntity<ApiDataResponse<List<Book>>> getBookByAuthor(@RequestParam Long authorId) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list books by author ID " + authorId + " successfully",
                bookService.getBooksByAuthor(authorId),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}

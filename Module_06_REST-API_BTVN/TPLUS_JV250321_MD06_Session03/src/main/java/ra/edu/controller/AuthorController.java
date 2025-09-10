package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Author;
import ra.edu.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Author>>> getAllAuthors() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Authors successfully",
                authorService.getAllAuthors(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Author>> insertAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new author successfully",
                authorService.createAuthor(author),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Author>> updateAuthor(@PathVariable Long id,@RequestBody Author author) {
        author.setId(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update author " + id + " successfully",
                authorService.updateAuthor(id, author),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Author>> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Delete author " + id + " successfully",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }
}

package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Category;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Category>>> getAllCategories() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Categories successfully",
                categoryService.getAllCategories(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Category>> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Get category " + id + " successfully",
                        categoryService.getCategoryById(id),
                        null,
                        HttpStatus.OK
                ),
        HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Category>> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new category successfully",
                categoryService.insertCategory(category),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Category>> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update category " + id + " successfully",
                categoryService.updateCategory(category),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Category>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Delete category " + id + " successfully",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }
}

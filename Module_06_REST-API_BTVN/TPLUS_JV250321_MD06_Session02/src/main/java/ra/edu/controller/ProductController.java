package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Product;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Product>>> getAllProducts() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Products successfully",
                productService.getAllProducts(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity<ApiDataResponse<List<Product>>> getAllProductsByCategory(@RequestParam Long categoryId) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Products by categoryId = " + categoryId + " successfully",
                productService.getAllProductsByCategoryId(categoryId),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiDataResponse<List<Product>>> getAllProductsByName(@RequestParam String keyword) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Products by keyword = " + keyword + " successfully",
                productService.getAllProductsByName(keyword),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Product>> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Get product " + id + " successfully",
                        productService.getProductById(id),
                        null,
                        HttpStatus.OK
                ),
        HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Product>> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new product successfully",
                productService.insertProduct(product),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update product " + id + " successfully",
                productService.updateProduct(product),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Product>> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Delete product " + id + " successfully",
                null,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}

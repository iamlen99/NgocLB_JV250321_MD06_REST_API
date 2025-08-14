package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.model.entity.Product;
import ra.edu.model.service.ProductService;
import ra.edu.storage.CloudinaryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productController")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CloudinaryService cloudinaryService;
    private final int pageSize = 3;

    @GetMapping(value = {"/", "/productPage"})
    public String productPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(value = "searchValue", required = false) String searchValue
            , Model model) {
        List<Product> products;
        int totalPages;

        if (searchValue == null || searchValue.isBlank()) {
            products = productService.getProducts(page, pageSize);
            totalPages = productService.getProductsTotalPages(pageSize);
            System.out.println(products.size());
        } else {
            products = productService.getProductsByName(searchValue, page, pageSize);
            totalPages = productService.getProductsTotalPages(searchValue, pageSize);
        }
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        model.addAttribute("totalPage", totalPages);
        return "productList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model) {
        model.addAttribute("product", new Product());
        return "addProductForm";
    }

    @GetMapping("/editPage")
    public String editPage(@RequestParam("id") int id, Model model) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "editProductForm";
        }
        return "redirect:/productController/productPage";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id, Model model) {
        if (productService.deleteProduct(id)) {
            return "redirect:/productController/productPage";
        }
        return "redirect:/productController/productPage";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                             @RequestParam("file") MultipartFile file,
                             Model model) {
        if(result.hasErrors()){
            return "addProductForm";
        }

        Optional<Product> productFound = productService.getProductByName(product.getProductName());
        if (productFound.isPresent()) {
            result.rejectValue("productName", "error.product", "Tên sản phẩm đã tồn tại");
            return "addProductForm";
        }

        if (file == null || file.isEmpty()) {
            model.addAttribute("posterError", "Chưa upload file hoặc file trống");
            return "addProductForm";
        }

        try {
            String imgUrl = cloudinaryService.uploadImage(file);
            product.setImageUrl(imgUrl);
        } catch (IOException e) {
            model.addAttribute("uploadError", "Lỗi upload file" + e.getMessage());
            return "addProductForm";
        }

        if(productService.addProduct(product)){
            return "redirect:/productController/productPage";
        }

        model.addAttribute("errMsg", "Có lỗi trong quá trình thêm sản phẩm, vui lòng thử lại");
        return "addProductForm";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                @RequestParam("file")  MultipartFile file,
                                Model model) {
        if(result.hasErrors()){
            return "editProductForm";
        }

        Optional<Product> productFound = productService.getProductByName(product.getProductName());
        if (productFound.isPresent()) {
            if(productFound.get().getProductId() != product.getProductId()) {
                result.rejectValue("productName", "error.product", "Tên sản phẩm đã tồn tại");
                return "editProductForm";
            }
        }

        if (file != null && !file.isEmpty()) {
            try {
                String imgUrl = cloudinaryService.uploadImage(file);
                product.setImageUrl(imgUrl);
            } catch (IOException e) {
                model.addAttribute("uploadError", "Lỗi upload file" + e.getMessage());
                return "editProductForm";
            }
        }

        if(productService.updateProduct(product)){
            return "redirect:/productController/productPage";
        }

        model.addAttribute("errMsg", "Có lỗi trong quá trình sửa thông tin sản phẩm, vui lòng thử lại");
        return "editProductForm";
    }
}

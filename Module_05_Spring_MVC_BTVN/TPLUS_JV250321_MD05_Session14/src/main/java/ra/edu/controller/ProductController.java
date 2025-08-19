package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Product;
import ra.edu.service.CategoryService;
import ra.edu.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/home")
    public String showProductsByCategories(@RequestParam(value = "searchValue", required = false, defaultValue = "")
                                           String searchValue,
                                           @RequestParam(value = "categoryId", required = false) Long categoryId,
                                           Model model) {
        List<Product> products;
        if (categoryId != null) {
            products = productService.findProductByNameAndCategory(searchValue, categoryId);
        } else {
            products = productService.findProductByName(searchValue);
        }
        model.addAttribute("currentCategoryId", categoryId);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "addProduct";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
                               Model model) {
        Product product = productService.findProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "editProduct";
        }
        model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id sản phẩm");
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        if (productService.deleteProduct(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa sản phẩm thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình xóa sản phẩm, xin thử lại");
        }
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addProduct";
        }
        if (productService.addProduct(product)) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm sản phẩm thành công");
            return "redirect:/products";
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("errMsg", "Có lỗi trong quá trình thêm sản phẩm, xin thử lại");
        return "addProduct";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "editProduct";
        }
        if (productService.updateProduct(product)) {
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi thông tin sản phẩm thành công");
            return "redirect:/products";
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("errMsg", "Có lỗi trong quá trình thay đổi thông tin sản phẩm, xin thử lại");
        return "editProduct";
    }
}

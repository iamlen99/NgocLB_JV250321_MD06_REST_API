package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Category;
import ra.edu.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("/add")
    public String showAddCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if(result.hasErrors()) {
            return "addCategory";
        }
        if (categoryService.addCategory(category)) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm danh mục thành công");
            return "redirect:/categories";
        }
        model.addAttribute("errMsg", "Có lỗi trong quá trình thêm danh mục, xin thử lại");
        return "addCategory";
    }
}

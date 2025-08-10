package ra.exercise.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;

@Controller
@RequestMapping(value = {"/", "categoryController"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categoryList";
    }

    @GetMapping("/goToAddPage")
    public String goToAddPage(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,  Model model) {
        if (bindingResult.hasErrors()) {
            return "addCategory";
        }
        if(categoryService.addCategory(category)) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("message", "Them danh muc thanh cong");
            return "categoryList";
        }
        model.addAttribute("error", "Co loi trong qua trinh them danh muc hoac ten danh muc da ton tai, vui long thu lai");
        return "addCategory";
    }
}

package ra.exercise.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;

import java.util.Optional;

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

    @GetMapping("/goToUpdatePage")
    public String goToUpdatePage(@RequestParam("id") int id, Model model) {
        Optional<Category> category = categoryService.getCategory(id);
        if (category.isEmpty()) {
            model.addAttribute("error", "Co loi trong qua trinh lay id danh muc");
            return "categoryList";
        }
        model.addAttribute("category", category.get());
        return "updateCategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,  Model model) {
        if (bindingResult.hasErrors()) {
            return "updateCategory";
        }
        if(categoryService.updateCategory(category)) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("message", "Sua danh muc thanh cong");
            return "categoryList";
        }
        model.addAttribute("error", "Co loi trong qua trinh sua danh muc, vui long thu lai");
        return "updateCategory";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory( @RequestParam("id") int id,  Model model) {
        if(categoryService.deleteCategory(id)) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("message", "Xoa danh muc thanh cong");
        } else {
            model.addAttribute("error", "Co loi trong qua trinh xoa danh muc, vui long thu lai");
        }
        return "categoryList";
    }
}

package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Category;
import ra.edu.model.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categoryController")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    private final int pageSize = 3;

    @GetMapping(value = {"/", "/categoryPage"})
    public String categoryPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(value = "searchValue", required = false) String searchValue
            , Model model) {
        List<Category> categories;
        int totalPages;

        if (searchValue == null || searchValue.isBlank()) {
            categories = categoryService.getCategories(page, pageSize);
            totalPages = categoryService.getCategoriesTotalPages(pageSize);
        } else {
            categories = categoryService.getCategoriesByName(searchValue, page, pageSize);
            totalPages = categoryService.getCategoriesTotalPages(searchValue, pageSize);
        }
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", categories);
        model.addAttribute("totalPage", totalPages);
        return "categoryList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model) {
        model.addAttribute("category", new Category());
        return "addCategoryForm";
    }

    @GetMapping("/editPage")
    public String editPage(@RequestParam("id") int id, Model model) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
            return "editCategoryForm";
        }
        return "redirect:/categoryController/categoryPage";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") int id, Model model) {
        if (categoryService.deleteCategory(id)) {
            return "redirect:/categoryController/categoryPage";
        }
        return "redirect:/categoryController/categoryPage";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "addCategoryForm";
        }

        Optional<Category> categoryFound = categoryService.getCategoryByName(category.getCategoryName());
        if (categoryFound.isPresent()) {
            result.rejectValue("categoryName", "error.category", "Tên danh mục đã tồn tại");
            return "addCategoryForm";
        }

        if(categoryService.addCategory(category)){
            return "redirect:/categoryController/categoryPage";
        }

        model.addAttribute("errMsg", "Có lỗi trong quá trình thêm danh mục, vui lòng thử lại");
        return "addCategoryForm";
    }

    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "editCategoryForm";
        }

        Optional<Category> categoryFound = categoryService.getCategoryByName(category.getCategoryName());
        if (categoryFound.isPresent()) {
            if(categoryFound.get().getCategoryId() != category.getCategoryId()) {
                result.rejectValue("categoryName", "error.category", "Tên danh mục đã tồn tại");
                return "editCategoryForm";
            }
        }

        if(categoryService.updateCategory(category)){
            return "redirect:/categoryController/categoryPage";
        }

        model.addAttribute("errMsg", "Có lỗi trong quá trình sửa thông tin danh mục, vui lòng thử lại");
        return "editCategoryForm";
    }
}

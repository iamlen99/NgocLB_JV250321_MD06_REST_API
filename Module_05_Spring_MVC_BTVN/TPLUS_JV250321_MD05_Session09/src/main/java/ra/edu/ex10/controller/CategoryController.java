package ra.edu.ex10.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ra.edu.ex10.model.Category_EN;
import ra.edu.ex10.model.Category_VI;
import ra.edu.ex10.service.CategoryService;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/categoryController")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categoriesEN")
    public String showCategoryEn(Model model, HttpSession session) {
        List<Category_EN> categoryEnList = categoryService.getCategoriesEN();
        model.addAttribute("categories", categoryEnList);
        model.addAttribute("currentLang", "EN");
        Locale locale = new Locale("en");
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        return "ex10/categoryList";
    }

    @GetMapping("/categoriesVI")
    public String showCategoryVi(Model model, HttpSession session) {
        List<Category_VI> categoryViList = categoryService.getCategoriesVI();
        model.addAttribute("categories", categoryViList);
        model.addAttribute("currentLang", "VI");
        Locale locale = new Locale("vi");
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        return "ex10/categoryList";
    }

    @GetMapping("/goToAddPage")
    public String addCategory(Model model) {
        model.addAttribute("categoriesVI", new Category_VI());
        model.addAttribute("categoriesEN", new Category_EN());
        return "ex10/addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("categoryNameVi") String categoryNameVi,
                              @RequestParam("categoryNameEn") String categoryNameEn,
                              @RequestParam("descriptionVi") String descriptionVi,
                              @RequestParam("descriptionEn") String descriptionEn,
                              Model model) {
        Category_VI categoryVI = new Category_VI();
        categoryVI.setCategoryName(categoryNameVi);
        categoryVI.setDescription(descriptionVi);

        Category_EN categoryEN = new Category_EN();
        categoryEN.setCategoryName(categoryNameEn);
        categoryEN.setDescription(descriptionEn);

        if(categoryService.addCategory(categoryVI, categoryEN)){
            List<Category_VI> categoryViList = categoryService.getCategoriesVI();
            model.addAttribute("categories", categoryViList);
            return "ex10/categoryList";
        };
        model.addAttribute("error", "Có lỗi trong quá trình thêm danh mục, xin thử lại");
        return "ex10/addCategory";
    }
}

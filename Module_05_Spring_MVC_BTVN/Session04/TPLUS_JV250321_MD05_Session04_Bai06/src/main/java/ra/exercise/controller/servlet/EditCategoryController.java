package ra.exercise.controller.servlet;

import java.io.*;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;
import ra.exercise.model.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/EditCategoryController")
public class EditCategoryController extends HttpServlet {
    public final CategoryService categoryService;

    public EditCategoryController() {
        categoryService = new CategoryServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.contains("updateCategory")) {
            int categoryId = Integer.parseInt(req.getParameter("id"));
            String categoryName = req.getParameter("cateName");
            String categoryDescription = req.getParameter("description");
            boolean categoryStatus = Boolean.parseBoolean(req.getParameter("status"));
            Category category = new Category(categoryId, categoryName, categoryDescription, categoryStatus);
            if(categoryService.updateCategory(category)) {
                List<Category> categoryList = categoryService.findAll();
                req.setAttribute("categories", categoryList);
                req.getRequestDispatcher("/view/categoryList.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Co loi trong qua trinh them danh muc");
                req.setAttribute("id", categoryId);
                req.setAttribute("cateName", categoryName);
                req.setAttribute("description", categoryDescription);
                req.setAttribute("status", categoryStatus);
                req.getRequestDispatcher("/view/editCategory.jsp").forward(req, resp);
            };
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.contains("updatePage")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Category> result = categoryService.findById(id);
            if (result.isPresent()) {
                Category category = result.get();
                req.setAttribute("category", category);
                req.getRequestDispatcher("/view/editCategory.jsp").forward(req, resp);
            } else {
                System.out.println("Co loi trong qua trinh tim danh muc san pham theo id");
            }

        }
    }
}
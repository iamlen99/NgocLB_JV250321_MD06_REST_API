package ra.exercise.controller.servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;
import ra.exercise.model.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/AddCategoryController")
public class AddCategoryController extends HttpServlet {
    public final CategoryService categoryService;

    public AddCategoryController() {
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.contains("addCategory")) {
            String categoryName = req.getParameter("cateName");
            String categoryDescription = req.getParameter("description");
            Category category = new Category();
            category.setCateName(categoryName);
            category.setDescription(categoryDescription);
            if(categoryService.addCategory(category)) {
                List<Category> categoryList = categoryService.findAll();
                req.setAttribute("categories", categoryList);
                req.getRequestDispatcher("/view/categoryList.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Co loi trong qua trinh them danh muc");
                req.setAttribute("cateName", categoryName);
                req.setAttribute("description", categoryDescription);
                req.getRequestDispatcher("/view/addCategory.jsp").forward(req, resp);
            };
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.contains("addPage")) {
            req.getRequestDispatcher("/view/addCategory.jsp").forward(req, resp);
        }
    }
}
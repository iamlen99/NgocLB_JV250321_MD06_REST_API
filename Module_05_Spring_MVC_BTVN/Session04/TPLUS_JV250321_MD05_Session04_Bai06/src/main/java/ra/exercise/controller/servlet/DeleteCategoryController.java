package ra.exercise.controller.servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;
import ra.exercise.model.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/DeleteCategoryController")
public class DeleteCategoryController extends HttpServlet {
    public final CategoryService categoryService;

    public DeleteCategoryController() {
        categoryService = new CategoryServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(categoryService.deleteCategory(id)) {
            List<Category> categoryList = categoryService.findAll();
            req.setAttribute("categories", categoryList);
        } else {
            req.setAttribute("error", "Co loi trong qua trinh xoa danh muc");
        }
        req.getRequestDispatcher("/view/categoryList.jsp").forward(req, resp);;
    }
}
package ra.exercise.controller.servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.dao.impl.CategoryDAOImpl;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;
import ra.exercise.model.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/CategoryController")
public class CategoryController extends HttpServlet {
    public final CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("findAll")) {
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/view/categoryList.jsp").forward(req, resp);
        }
    }
}
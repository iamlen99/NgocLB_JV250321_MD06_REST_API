package ra.exercise.controller.servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.dao.CategoryDAO;
import ra.exercise.model.entity.Category;

@WebServlet(urlPatterns = "/CategoryController")
public class CategoryController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("findAll")) {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categories = categoryDAO.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/view/categoryList.jsp").forward(req, resp);
        }
    }
}
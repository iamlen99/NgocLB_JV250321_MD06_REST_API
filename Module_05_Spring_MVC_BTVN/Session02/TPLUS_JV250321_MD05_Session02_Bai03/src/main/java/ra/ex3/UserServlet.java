package ra.ex3;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    private List<User> users = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(name, email, password);
        users.add(user);

        req.setAttribute("message", "Dang ky thanh cong");
        req.setAttribute("users", users);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
package ra.ex2;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/userServlet")
public class userServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        req.setAttribute("name", name);
        req.setAttribute("email", email);
        RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
        rd.forward(req, resp);
    }
}
package ra.exercise;

import java.io.*;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Repository repository = new Repository();
        Optional<Customer> customer = repository.getCustomer(username, password);

        if (customer.isPresent()) {
            UserSession.customer = customer.get();
            if(customer.get().getRole() == Role.ADMIN) {
                req.getRequestDispatcher("admin.jsp").forward(req, resp);
            } else  {
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
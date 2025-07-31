package ra.tplus_jv250321_md05_session01_bai04;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/servlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = "NguyenVanA";
        String password = "matkhauco8kytu";

        String usernameInput = req.getParameter("username");
        String passwordInput = req.getParameter("password");

        if (usernameInput.equals(username) && passwordInput.equals(password)) {
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Invalid username or password.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
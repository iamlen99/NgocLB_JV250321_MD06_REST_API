package ra.exercise;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import validation.Validator;

@WebServlet(urlPatterns = "/servlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String confirmPassword = req.getParameter("confirm-password");

        req.setAttribute("usernameValue", username);
        req.setAttribute("emailValue", email);

        boolean hasError = false;

        if (Validator.isValidUsername(username) != null) {
            req.setAttribute("usernameError", Validator.isValidUsername(username));
            hasError = true;
        }

        if (Validator.isValidPassword(password) != null) {
            req.setAttribute("passwordError", Validator.isValidPassword(password));
            hasError = true;
        }

        if (Validator.isValidEmail(email) != null) {
            req.setAttribute("emailError", Validator.isValidEmail(email));
            hasError = true;
        }

        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            req.setAttribute("confirmPasswordError", "Confirm Password is required");
            hasError = true;
        } else if (!confirmPassword.equals(password)) {
            req.setAttribute("confirmPasswordError", "Confirm Password does not match");
            hasError = true;
        }

        if (!hasError) {
            req.setAttribute("message", "Registered Successfully");
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
package com.example.tplus_jv250321_md05_session01_bai02;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/DemoServlet")
public class DemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = "1";
        String name = "Huan";
        String email = "Huan rose@gmail.com";
        String age = "18";

        req.setAttribute("id", id);
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("age", age);

        req.getRequestDispatcher("demo.jsp").forward(req, resp);
    }

}
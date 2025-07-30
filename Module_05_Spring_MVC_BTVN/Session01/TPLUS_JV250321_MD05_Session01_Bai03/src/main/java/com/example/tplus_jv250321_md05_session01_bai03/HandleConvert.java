package com.example.tplus_jv250321_md05_session01_bai03;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/HandleConvert")
public class HandleConvert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String rateStr = req.getParameter("rate");
            String usdStr = req.getParameter("usd");

            if (rateStr == null || rateStr.trim().isEmpty() ||
                    usdStr == null || usdStr.trim().isEmpty()) {
                req.setAttribute("error", "Vui long nhap day du ca Rate va USD.");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                return;
            }

            double rate = Double.parseDouble(req.getParameter("rate"));
            double usd = Double.parseDouble(req.getParameter("usd"));
            double vnd = rate * usd;

            req.setAttribute("rate", rate);
            req.setAttribute("usd", usd);
            req.setAttribute("vnd", vnd);

            RequestDispatcher dispatcher = req.getRequestDispatcher("converted-info.jsp");
            dispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Vui long nhap dung dinh dang so.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
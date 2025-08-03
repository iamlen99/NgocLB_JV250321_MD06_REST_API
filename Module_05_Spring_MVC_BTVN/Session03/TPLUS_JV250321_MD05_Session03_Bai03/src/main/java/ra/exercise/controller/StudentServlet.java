package ra.exercise.controller;

import ra.exercise.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    List<Student> students = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.trim().equals("addStudent")) {
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            int age = Integer.parseInt(req.getParameter("age"));

            if(age < 0) {
                req.setAttribute("message", "Them sinh vien that bai");
                req.setAttribute("error", "Tuoi phai lon hon hoac bang 0");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                students.add(new Student(name, age, address));
                req.getSession().setAttribute("students", students);
                req.getRequestDispatcher("/view/studentList.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.trim().equals("addStudent")) {
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            int age = Integer.parseInt(req.getParameter("age"));

            if(age < 18) {
                req.setAttribute("name", name);
                req.setAttribute("address", address);
                req.setAttribute("age", age);
                req.setAttribute("message", "Them sinh vien that bai");
                req.setAttribute("error", "Tuoi phai lon hon hoac bang 18");
                req.getRequestDispatcher("/view/addStudent.jsp").forward(req, resp);
            } else {
                List<Student> students = (List<Student>) req.getSession().getAttribute("students");
                if (students == null) {
                    students = new ArrayList<>();
                }
                students.add(new Student(name, age, address));
                req.getSession().setAttribute("students", students);
                req.getRequestDispatcher("/view/studentList.jsp").forward(req, resp);
            }
        } else if (action.trim().equals("UpdateStudent")) {
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            int age = Integer.parseInt(req.getParameter("age"));
            int idx = Integer.parseInt(req.getParameter("idx"));

            if(age < 18) {
                Student oldStudent = new Student(name, age, address);
                req.setAttribute("idx", idx);
                req.setAttribute("student", oldStudent);
                req.setAttribute("message", "Cap nhat thong tin sinh vien that bai");
                req.setAttribute("error", "Tuoi phai lon hon hoac bang 18");
                req.getRequestDispatcher("/view/update.jsp").forward(req, resp);
            } else {
                List<Student> students = (List<Student>) req.getSession().getAttribute("students");
                if (students != null && idx < students.size()) {
                    Student student = students.get(idx);
                    student.setName(name);
                    student.setAge(age);
                    student.setAddress(address);
                    req.getSession().setAttribute("students", students);
                    req.getRequestDispatcher("/view/studentList.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/view/update.jsp");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.trim().equals("Update")) {
            int idx = Integer.parseInt(req.getParameter("idx"));
            List<Student> students = (List<Student>) req.getSession().getAttribute("students");
            if (students != null && idx < students.size()) {
                req.setAttribute("student", students.get(idx));
                req.setAttribute("idx", idx);
                req.getRequestDispatcher("/view/update.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/addStudent.jsp"); //
            }
        } else if (action.trim().equals("deleteStudent")) {
            int idx = Integer.parseInt(req.getParameter("idx"));
            List<Student> students = (List<Student>) req.getSession().getAttribute("students");
            if (students == null) {
                students = new ArrayList<>();
            }
            students.remove(idx);
            req.getSession().setAttribute("students", students);
            req.getRequestDispatcher("/view/studentList.jsp").forward(req, resp);
        }
    }
}
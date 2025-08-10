package ra.exercise.controller;

import java.io.*;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;
import ra.exercise.model.service.impl.MovieServiceImpl;

@WebServlet(urlPatterns = "/DeleteMovieServlet")
public class DeleteMovieServlet extends HttpServlet {
    public final MovieService movieService;
    public DeleteMovieServlet() {
        movieService = new MovieServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("deleteMovie")) {
            long id = Long.parseLong(req.getParameter("id"));
            if(movieService.deleteMovie(id)){
                resp.sendRedirect(req.getContextPath() + "/MovieListServlet");
            } else {
                req.setAttribute("message", "Co loi trong qua trinh xoa phim, vui long thu lai");
                req.getRequestDispatcher("/view/listMovie.jsp").forward(req, resp);
            }
        }
    }
}
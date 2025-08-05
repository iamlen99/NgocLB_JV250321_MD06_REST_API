package ra.exercise.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;
import ra.exercise.model.service.impl.MovieServiceImpl;

@WebServlet(urlPatterns = "/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
    public final MovieService movieService;
    public AddMovieServlet() {
        movieService = new MovieServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("addMovie")) {
            Movie movie = new Movie();
            movie.setTitle(req.getParameter("title"));
            movie.setDirector(req.getParameter("director"));
            movie.setGenre(req.getParameter("genre"));
            movie.setDescription(req.getParameter("description"));
            movie.setDuration(Integer.parseInt(req.getParameter("duration")));
            movie.setLanguage(req.getParameter("language"));
            if(movieService.addMovie(movie)) {
                resp.sendRedirect(req.getContextPath() + "/MovieListServlet");
            } else  {
                req.setAttribute("message", "Them phim that bai, vui long thu lai");
                req.setAttribute("movie", movie);
                req.getRequestDispatcher("/view/formAddMovie.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("goToAddPage")) {
            req.getRequestDispatcher("/view/formAddMovie.jsp").forward(req, resp);
        }
    }
}
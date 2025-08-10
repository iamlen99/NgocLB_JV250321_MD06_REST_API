package ra.exercise.controller;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;
import ra.exercise.model.service.impl.MovieServiceImpl;

@WebServlet(urlPatterns = "/MovieListServlet")
public class MovieListServlet extends HttpServlet {
    public final MovieService movieService;

    public MovieListServlet() {
        movieService = new MovieServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movieList = movieService.findAll();
        req.setAttribute("movieList", movieList);
        req.getRequestDispatcher("/view/listMovie.jsp").forward(req, resp);
    }
}
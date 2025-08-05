package ra.exercise.controller;

import java.io.*;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;
import ra.exercise.model.service.impl.MovieServiceImpl;

@WebServlet(urlPatterns = "/DisplayMovieDetailServlet")
public class DisplayMovieDetailServlet extends HttpServlet {
    public final MovieService movieService;

    public DisplayMovieDetailServlet() {
        movieService = new MovieServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("displayDetail")) {
            long movieId = Long.parseLong(req.getParameter("id"));
            Optional<Movie> movie = movieService.findById(movieId);
            if(movie.isPresent()) {
                req.getSession().setAttribute("movie", movie.get());
                req.getRequestDispatcher("/view/movieDetails.jsp").forward(req, resp);
            }
        }
    }
}
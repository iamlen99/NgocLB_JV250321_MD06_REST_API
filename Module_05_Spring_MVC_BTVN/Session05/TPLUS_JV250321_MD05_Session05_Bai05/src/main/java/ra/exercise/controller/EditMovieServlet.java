package ra.exercise.controller;

import java.io.*;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;
import ra.exercise.model.service.impl.MovieServiceImpl;

@WebServlet(urlPatterns = "/EditMovieServlet")
public class EditMovieServlet extends HttpServlet {
    public final MovieService movieService;
    public EditMovieServlet() {
        movieService = new MovieServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("updateMovie")) {
            Movie movie = new Movie();
            movie.setMovieId(Integer.parseInt(req.getParameter("id")));
            movie.setTitle(req.getParameter("title"));
            movie.setDirector(req.getParameter("director"));
            movie.setGenre(req.getParameter("genre"));
            movie.setDescription(req.getParameter("description"));
            movie.setDuration(Integer.parseInt(req.getParameter("duration")));
            movie.setLanguage(req.getParameter("language"));

            if(movieService.updateMovie(movie)) {
                resp.sendRedirect(req.getContextPath() + "/MovieListServlet");
            } else  {
                req.setAttribute("message", "Sua phim that bai, vui long thu lai");
                req.setAttribute("movie", movie);
                req.getRequestDispatcher("/view/formUpdateMovie.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("goToUpdatePage")) {
            long id = Long.parseLong(req.getParameter("id"));
            Optional<Movie> result = movieService.findById(id);
            Movie movie = result.get();
            req.setAttribute("movie", movie);
            req.getRequestDispatcher("/view/formUpdateMovie.jsp").forward(req, resp);
        }
    }
}
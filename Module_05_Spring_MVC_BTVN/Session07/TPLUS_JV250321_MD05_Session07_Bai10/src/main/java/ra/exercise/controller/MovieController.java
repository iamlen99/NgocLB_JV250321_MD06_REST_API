package ra.exercise.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;
import ra.exercise.storage.CloudinaryService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/", "movieController"})
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private CloudinaryService cloudinaryService;


    @GetMapping
    public String showMovie(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movieList";
    }

    @GetMapping("/goToAddPage")
    public String goToAddPage(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            return "addMovie";
        }

        if (file == null || file.isEmpty()) {
            model.addAttribute("posterError", "Chưa upload file hoặc file trống");
            return "addMovie";
        }

        try {
            String imgUrl = cloudinaryService.uploadImage(file);
            movie.setPoster(imgUrl);
        } catch (IOException e) {
            model.addAttribute("uploadError", "Lỗi upload file" + e.getMessage());
            return "addMovie";
        }

        if (movieService.addMovie(movie)) {
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("message", "Them phim thanh cong");
            return "movieList";
        }
        model.addAttribute("error", "Co loi trong qua trinh them phim, vui long thu lai");
        return "addMovie";
    }

    @GetMapping("/goToUpdatePage")
    public String goToUpdatePage(@RequestParam("id") Integer id, Model model) {
        Optional<Movie> movie = movieService.getMovie(id);
        if (movie.isEmpty()) {
            model.addAttribute("error", "Co loi trong qua trinh lay id san pham");
            return "movieList";
        }
        model.addAttribute("movie", movie.get());
        return "updateMovie";
    }

    @PostMapping("/updateMovie")
    public String updateMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            return "updateMovie";
        }

        if (file != null && !file.isEmpty()) {
            try {
                String imgUrl = cloudinaryService.uploadImage(file);
                movie.setPoster(imgUrl);
            } catch (IOException e) {
                model.addAttribute("uploadError", "Lỗi upload file" + e.getMessage());
                return "updateMovie";
            }
        }

        if (movieService.updateMovie(movie)) {
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("message", "Sua phim thanh cong");
            return "movieList";
        }
        model.addAttribute("error", "Co loi trong qua trinh sua phim, vui long thu lai");
        return "updateMovie";
    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(@RequestParam("id") Integer id, Model model) {
        if (movieService.deleteMovie(id)) {
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("message", "Xoa phim thanh cong");
        } else {
            model.addAttribute("error", "Co loi trong qua trinh xoa phim, vui long thu lai");
        }
        return "movieList";
    }
}

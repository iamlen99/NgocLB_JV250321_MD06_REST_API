package ra.exercise.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Movie {
    private Integer id;

    @NotBlank(message = "Ten phim khong duoc de trong")
    @Size(max = 100, message = "Ten phim co do dai toi da 100 ky tu")
    private String title;

    @NotBlank(message = "Ten dao dien khong duoc de trong")
    @Size(max = 50, message = "Ten dao dien co do dai toi da 50 ky tu")
    private String director;

    @NotNull(message = "Ngay phat hanh khong duoc de trong")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotBlank(message = "Ten the loai phim khong duoc de trong")
    @Size(max = 30, message = "Ten the loai phim co do dai toi da 30 ky tu")
    private String genre;

    @NotBlank(message = "Poster khong duoc de trong")
    private String poster;

    public Movie(Integer id, String title, String director, LocalDate releaseDate, String genre, String poster) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.poster = poster;
    }

    public Movie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}

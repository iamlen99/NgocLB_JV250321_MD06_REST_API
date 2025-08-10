package ra.exercise.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {
    private long movieId;
    private String title;
    private String director;
    private String genre;
    private String description;
    private int duration;
    private String language;
}

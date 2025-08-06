package ra.exercise;

import org.springframework.stereotype.Component;
import ra.exercise.model.entity.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Data {
    private final List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question(1, "https://example.com/image1.jpg", "Dog"),
            new Question(2, "https://example.com/image2.jpg", "Cat"),
            new Question(3, "https://example.com/image3.jpg", "Apple"),
            new Question(4, "https://example.com/image4.jpg", "Car"),
            new Question(5, "https://example.com/image5.jpg", "Bird"),
            new Question(6, "https://example.com/image6.jpg", "Book"),
            new Question(7, "https://example.com/image7.jpg", "Tree"),
            new Question(8, "https://example.com/image8.jpg", "Sun"),
            new Question(9, "https://example.com/image9.jpg", "Chair"),
            new Question(10, "https://example.com/image10.jpg", "Computer")
    ));


    public Question getQuestion() {
        return questions.get((int) (Math.random() * questions.size()));
    }
}

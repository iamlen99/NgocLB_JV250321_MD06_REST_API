package ra.exercise;

import org.springframework.stereotype.Component;
import ra.exercise.model.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

@Component
public class Data {
    public List<User> users = new ArrayList<>( Arrays.asList(
            new User("Nguyễn Văn A", 25, LocalDate.of(2000, 6, 15), "a@example.com", "0901234567"),
            new User("Trần Thị B", 30, LocalDate.of(1995, 4, 10), "b@example.com", "0912345678"),
            new User("Lê Văn C", 22, LocalDate.of(2002, 12, 1), "c@example.com", "0923456789"),
            new User("Phạm Minh D", 28, LocalDate.of(1997, 8, 20), "d@example.com", "0934567890"),
            new User("Hoàng Thị E", 27, LocalDate.of(1998, 3, 5), "e@example.com", "0945678901"),
            new User("Đặng Văn F", 35, LocalDate.of(1989, 11, 12), "f@example.com", "0956789012"),
            new User("Lý Thị G", 23, LocalDate.of(2001, 2, 28), "g@example.com", "0967890123"),
            new User("Võ Văn H", 26, LocalDate.of(1999, 9, 17), "h@example.com", "0978901234"),
            new User("Bùi Thị I", 29, LocalDate.of(1996, 5, 9), "i@example.com", "0989012345"),
            new User("Ngô Văn J", 24, LocalDate.of(2001, 7, 30), "j@example.com", "0990123456")
    ));

    public List<User> getUsers() {
        return users;
    }
}

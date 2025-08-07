package ra.exercise.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    private long id;
    private long movieId;
    private long screenRoomId;
    private LocalDateTime showTime;
    private String format;
    private int availableSeat;
}

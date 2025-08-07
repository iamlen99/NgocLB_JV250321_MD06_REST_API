package ra.exercise.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenRoom {
    private long id;
    private String screenRoomName;
    private int totalSeat;
}

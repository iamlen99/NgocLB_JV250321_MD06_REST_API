package ra.edu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Integer id;
    private String roomName;
    private String roomType;
    private RoomStatus status;
    private Boolean delete;
    private Double price;
    private String image;

    public boolean isDelete() {
        return delete;
    }
}

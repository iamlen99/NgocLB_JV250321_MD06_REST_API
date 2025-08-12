package ra.edu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private int id;
    private int roomId;
    private int customerId;
    private String checkInDate;
    private String checkOutDate;
    private int guests;
    private String notes;
}


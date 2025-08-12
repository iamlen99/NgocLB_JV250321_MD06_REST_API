package ra.edu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long id;
    private Long customerId;
    private Integer roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalPrice;
    private LocalDateTime bookingDate;
    private String status;
}




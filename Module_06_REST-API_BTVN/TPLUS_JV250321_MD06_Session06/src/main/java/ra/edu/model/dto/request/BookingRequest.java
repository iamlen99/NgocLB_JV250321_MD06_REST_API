package ra.edu.model.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.entity.RoomType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull(message = "UserId must not be null")
    private Long userId;

    @NotNull(message = "RoomId must not be null")
    private Long roomId;

    @NotNull(message = "Check-in date must not be null")
    private LocalDateTime checkInDate;

    @NotNull(message = "Check-out date must not be null")
    private LocalDateTime checkOutDate;
}


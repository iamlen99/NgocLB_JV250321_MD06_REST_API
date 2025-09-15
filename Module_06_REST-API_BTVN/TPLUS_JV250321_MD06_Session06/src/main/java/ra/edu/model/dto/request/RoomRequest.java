package ra.edu.model.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.entity.RoomType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    @NotBlank(message = "Room Number must not be blank")
    private String roomNumber;

    @NotNull(message = "Room Type must not be null")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.01", message = "Price must greater than 0")
    private  Double price;

    @NotNull(message = "Capacity must not be null")
    @DecimalMin(value = "0.01", message = "Capacity must greater than 0")
    private  Integer capacity;

    @NotNull(message = "Room Status must must not be null")
    private RoomStatus status;

    private MultipartFile imageFile;
}

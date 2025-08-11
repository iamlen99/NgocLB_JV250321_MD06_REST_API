package ra.edu.ex08_09.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private int id;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Giá trị giao dịch không được để trống")
    @DecimalMin(value = "0.01", message = "Gía trị giao dịch không được âm")
    private Double amount;
    private boolean type;
}

package ra.edu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.model.entity.CustomerStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatusUpdateRequest {
    private CustomerStatus status;
}


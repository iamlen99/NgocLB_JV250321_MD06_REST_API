package ra.edu.ex05_06.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {
    private Product product;
    private int quantity;
}

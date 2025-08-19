package ra.edu.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long id;
    private Product product;
    private Integer quantity;

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

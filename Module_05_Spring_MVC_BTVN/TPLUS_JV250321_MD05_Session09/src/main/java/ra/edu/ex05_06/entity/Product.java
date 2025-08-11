package ra.edu.ex05_06.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private Double price;
    private String description;
    private String image;
    private int quantity;
}

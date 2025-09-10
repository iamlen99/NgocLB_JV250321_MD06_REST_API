package ra.edu.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INT CHECK (quantity >0)")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

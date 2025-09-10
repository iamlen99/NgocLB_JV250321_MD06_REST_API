package ra.edu.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime borrowDate;
    @Column
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

package ra.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.StatusResponse;
import ra.edu.model.entity.Order;
import ra.edu.model.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private List<Order> orders = Arrays.asList(
            new Order(1L, "ORD123", "Pending"),
            new Order(2L, "ORD124", "Shipped"),
            new Order(3L, "ORD125", "Delivered")
    );

    @GetMapping("/{id}/status")
    public ResponseEntity<?> getOrderStatus(@PathVariable Long id) {
        Optional<Order> order = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();

        if (order.isPresent()) {
            return ResponseEntity.ok(
                    new StatusResponse(order.get().getOrderNumber(), order.get().getStatus())
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order not found");
        }
    }
}

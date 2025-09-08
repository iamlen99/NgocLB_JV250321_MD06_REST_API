package ra.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final List<Task> tasks = new ArrayList<>();
    private long currentId = 1;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 nếu title rỗng
        }
        task.setId(currentId++);
        if (task.getStatus() == null) {
            task.setStatus("Pending");
        }
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task); // 201 Created
    }
}

package ra.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.response.ApiDataResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<String>>> getListUsers() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list users successfully!",
                List.of("User1", "User2", "User3", "User4"),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}

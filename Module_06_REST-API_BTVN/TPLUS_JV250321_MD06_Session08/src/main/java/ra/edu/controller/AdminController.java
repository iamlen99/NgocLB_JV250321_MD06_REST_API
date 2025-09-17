package ra.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.response.ApiDataResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/admins")
public class AdminController {
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<String>>> getListAdmins() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list admins successfully!",
                List.of("Admin1", "Admin2", "Admin3", "Admin4"),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}

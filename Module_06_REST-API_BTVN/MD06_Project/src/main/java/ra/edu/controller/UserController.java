package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Users;
import ra.edu.model.request.RoleRequest;
import ra.edu.model.request.UserRegister;
import ra.edu.model.request.UserUpdate;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Users>> createUser(@Valid @RequestBody UserRegister userRegister) {
        Users registeredUser = userService.createUser(userRegister);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(registeredUser, "Đăng ký tài khoản thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Users>>> getAllUsers(
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Users> users = userService.getAllUsers(page, size, role);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(users, "Lấy danh sách người dùng thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Users>> getUserById(
            @PathVariable Long id
    ) {
        Users user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(user, "Lấy thông tin người dùng thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Users>> updateUserById(
            @Valid @RequestBody UserUpdate userUpdate,
            @PathVariable Long id
    ) {
        Users user = userService.updateUser(id, userUpdate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(user, "Cập nhật thông tin người dùng thành công"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiDataResponse<Users>> updateStatusUser(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        Users user = userService.updateUserStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(user, "Cập nhật trạng thái kích hoạt của người dùng thành công"));
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<ApiDataResponse<Users>> updateRolesUser(
            @Valid @RequestBody RoleRequest roleRequest,
            @PathVariable Long id
            ) {
        Users user = userService.updateUser(id, roleRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(user, "Cập nhật vai trò của người dùng thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Users>> deleteUserById(
            @PathVariable Long id
    ) {
        Users deletedUser = userService.findById(id);
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(deletedUser, "Xóa người dùng thành công"));
    }
}

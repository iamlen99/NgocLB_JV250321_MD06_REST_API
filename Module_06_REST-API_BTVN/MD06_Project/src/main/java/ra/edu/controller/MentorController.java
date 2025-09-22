package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Mentor;
import ra.edu.model.request.MentorRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.MentorService;

@RestController
@RequestMapping("/api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Mentor>> createMentor(@Valid @RequestBody MentorRequest mentorRequest) {
        Mentor mentor = mentorService.createMentor(mentorRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(mentor, "Tạo thông tin sinh viên thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Mentor>>> getAllMentors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Mentor> mentors = mentorService.getAllMentor(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentors, "Lấy danh sách sinh viên thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Mentor>> getMentorById(
            @PathVariable Long id
    ) {
        Mentor mentor = mentorService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentor, "Lấy thông tin sinh viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Mentor>> updateMentor(
            @Valid @RequestBody MentorRequest mentorRequest
    ) {
        Mentor mentorUpdate = mentorService.updateMentor(mentorRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentorUpdate, "Cập nhật thông tin sinh viên thành công"));
    }
}

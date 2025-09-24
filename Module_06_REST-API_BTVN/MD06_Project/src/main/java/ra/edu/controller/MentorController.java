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
import ra.edu.model.response.MentorDetailsResponse;
import ra.edu.model.response.MentorResponse;
import ra.edu.service.MentorService;

@RestController
@RequestMapping("/api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;

    @GetMapping("/admin")
    public ResponseEntity<ApiDataResponse<Page<MentorDetailsResponse>>> getAllMentorsDetails(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<MentorDetailsResponse> mentors = mentorService.getAllMentorsDetails(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentors, "Lấy danh sách giáo viên hướng dẫn thành công"));
    }

    @GetMapping("/student")
    public ResponseEntity<ApiDataResponse<Page<MentorResponse>>> getAllMentors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<MentorResponse> mentors = mentorService.getAllMentors(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentors, "Lấy danh sách giáo viên hướng dẫn thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MentorDetailsResponse>> getMentorById(
            @PathVariable Long id
    ) {
        MentorDetailsResponse mentor = mentorService.findDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentor, "Lấy thông tin giáo viên hướng dẫn thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Mentor>> createMentor(@Valid @RequestBody MentorRequest mentorRequest) {
        Mentor mentor = mentorService.createMentor(mentorRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(mentor, "Tạo thông tin giáo viên hướng dẫn thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Mentor>> updateMentor(
            @Valid @RequestBody MentorRequest mentorRequest,
            @PathVariable Long id
    ) {
        Mentor mentorUpdate = mentorService.updateMentor(id, mentorRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(mentorUpdate, "Cập nhật thông tin giáo viên hướng dẫn thành công"));
    }
}

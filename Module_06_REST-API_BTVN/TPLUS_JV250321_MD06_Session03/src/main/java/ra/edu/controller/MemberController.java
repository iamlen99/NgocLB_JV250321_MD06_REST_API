package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Member;
import ra.edu.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Member>>> getAllMembers() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Members successfully",
                memberService.getAllMembers(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Member>> insertMember(@RequestBody Member member) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new member successfully",
                memberService.createMember(member),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Member>> updateMember(@PathVariable Long id,@RequestBody Member member) {
        member.setId(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update member " + id + " successfully",
                memberService.updateMember(id, member),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Member>> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Delete member " + id + " successfully",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }
}

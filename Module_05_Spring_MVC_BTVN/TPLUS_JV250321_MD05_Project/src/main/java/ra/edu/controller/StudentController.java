package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.ChangePasswordDTO;
import ra.edu.model.dto.CourseDTO;
import ra.edu.model.dto.UserUpdate;
import ra.edu.model.entity.Course;
import ra.edu.model.entity.Enrollment;
import ra.edu.model.entity.User;
import ra.edu.service.CourseService;
import ra.edu.service.EnrollmentService;
import ra.edu.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public String showStudentPage() {
        return "student/student-index";
    }

    @GetMapping("/show-course-list")
    public String showCourseList(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        Page<Course> courses;
        if (searchValue == null || searchValue.isBlank()) {
            courses = courseService.getAllCourses(page, size, null);
        } else {
            courses = courseService.getAllCoursesByName(page, size, searchValue, null);
        }
        model.addAttribute("courses", courses);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("page", page);
        return "student/course/course-list";
    }

    @GetMapping("/register-course")
    public String showRegisterCoursePage(
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "student/course/course-register";
    }

    @PostMapping("/register-course")
    public String registerCourse(
            @RequestParam(value = "courseId", required = false) Long courseId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        if (courseId == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Vui lòng chọn khóa học cần đăng ký");
            return "redirect:/student/register-course";
        }

        Optional<Course> courseOptional = courseService.findCourseById(courseId);
        if (courseOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy id khóa học, xin thử lại");
            return "redirect:/student/register-course";
        }

        Enrollment enrollmentCheck = enrollmentService.getEnrollmentByCourseIdAndStudentId(courseId, loggedUser.getId());
        if (enrollmentCheck != null) {
            redirectAttributes.addFlashAttribute("errMsg", "Bạn đã đăng ký khóa học này rồi");
            return "redirect:/student/register-course";
        }

        Enrollment enrollment = new Enrollment();
        Course course = courseOptional.get();
        enrollment.setCourse(course);
        enrollment.setUser(loggedUser);

        try {
            Enrollment savedEnrollment = enrollmentService.saveEnrollment(enrollment);
            redirectAttributes.addFlashAttribute("successMsg", "Đăng ký khóa học " +
                    savedEnrollment.getCourse().getName() + " thành công");
            return "redirect:/student";
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi đăng ký khóa học, xin thử lại");
            return "redirect:/student/register-course";
        }
    }

    @GetMapping("/show-enrollment-history")
    public String showEnrollmentHistoryList(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        Page<Enrollment> enrollments;
        if (searchValue == null || searchValue.isBlank()) {
            enrollments = enrollmentService.getEnrollmentByStudentId(loggedUser.getId(), page, size);
        } else {
            enrollments = enrollmentService.getEnrollmentByStudentIdAndSearchValue(loggedUser.getId(), page, size, searchValue);
        }
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("page", page);
        return "student/enrollment/enrollment-list";
    }

    @GetMapping("/cancel-enrollment/{id}")
    public String cancelEnrollment(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        try {
            enrollmentService.cancelEnrollment(id);
            redirectAttributes.addFlashAttribute("successMsg", "Hủy đăng ký khóa học thành công");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình hủy đăng ký khóa học");
        }
        return "redirect:/student/show-enrollment-history";
    }

    @GetMapping("/edit")
    public String showEditStudentPage(
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        UserUpdate studentUpdate = new UserUpdate();
        studentUpdate.setName(loggedUser.getName());
        studentUpdate.setDob(loggedUser.getDob());
        studentUpdate.setEmail(loggedUser.getEmail());
        studentUpdate.setPhone(loggedUser.getPhone());
        studentUpdate.setSex(loggedUser.getSex());
        model.addAttribute("studentUpdate", studentUpdate);
        return "student/edit-student";
    }

    @PostMapping("/update")
    public String updateStudent(
            @Valid @ModelAttribute("studentUpdate") UserUpdate studentUpdate,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession session,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "student/edit-student";
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        if (!loggedUser.getEmail().equals(studentUpdate.getEmail()) &&
                userService.isExistEmail(studentUpdate.getEmail())
        ) {
            bindingResult.rejectValue("email", "err.email", "Email đã tồn tại");
            return "student/edit-student";
        }

        if (!loggedUser.getPhone().equals(studentUpdate.getPhone()) &&
                userService.isExistPhone(studentUpdate.getPhone())
        ) {
            model.addAttribute("studentUpdate", studentUpdate);
            bindingResult.rejectValue("phone", "err.phone", "Số điện thoại đã tồn tại");
            return "student/edit-student";
        }

        Optional<User> userOpt = userService.findById(loggedUser.getId());
        if (userOpt.isEmpty()) {
            model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id tài khoản, xin thử lại");
            return "student/edit-student";
        }

        User userUpdate = userOpt.get();

        userUpdate.setName(studentUpdate.getName());
        userUpdate.setDob(studentUpdate.getDob());
        userUpdate.setEmail(studentUpdate.getEmail());
        userUpdate.setSex(studentUpdate.getSex());
        userUpdate.setPhone(studentUpdate.getPhone());

        try {
            userService.save(userUpdate);
            session.setAttribute("loggedUser", userUpdate);
            redirectAttributes.addFlashAttribute("successMsg", "Cập nhật thông tin tài khoản thành công");
            return "redirect:/student";
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("errMsg", "Có lỗi trong quá trình cập nhật: " + e.getMessage());
            return "student/edit-student";
        }
    }

    @GetMapping("/edit-password")
    public String showEditPasswordPage(
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        model.addAttribute("changePasswordDTO", changePasswordDTO);
        return "student/edit-password";
    }

    @PostMapping("/update-password")
    public String changePassword(
            @Valid @ModelAttribute("changePasswordDTO") ChangePasswordDTO changePasswordDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession session,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "student/edit-password";
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        if (!BCrypt.checkpw(changePasswordDTO.getCurrentPassword(), loggedUser.getPassword())) {
            bindingResult.rejectValue("currentPassword", "err.currentPassword", "Mật khẩu hiện tại không đúng");
            return "student/edit-password";
        }
        ;

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            bindingResult.rejectValue("confirmNewPassword", "err.confirmNewPassword", "Mật khẩu xác nhận không trùng khớp");
            return "student/edit-password";
        }
        ;

        Optional<User> userOpt = userService.findById(loggedUser.getId());
        if (userOpt.isEmpty()) {
            model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id tài khoản, xin thử lại");
            return "student/edit-password";
        }

        User userUpdate = userOpt.get();

        String hashedPassword = BCrypt.hashpw(changePasswordDTO.getNewPassword(), BCrypt.gensalt(12));
        userUpdate.setPassword(hashedPassword);
        loggedUser.setPassword(hashedPassword);

        try {
            userService.save(userUpdate);
            session.setAttribute("loggedUser", userUpdate);
            redirectAttributes.addFlashAttribute("successMsg", "Cập nhật mật khẩu thành công");
            return "redirect:/student";
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("errMsg", "Có lỗi trong quá trình cập nhật: " + e.getMessage());
            return "student/edit-password";
        }
    }
}

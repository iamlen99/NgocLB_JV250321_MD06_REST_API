package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.CourseDTO;
import ra.edu.model.entity.Course;
import ra.edu.model.entity.User;
import ra.edu.service.CourseService;
import ra.edu.storage.CloudinaryService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseManagementController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String showCourses(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "searchValue", required = false) String searchValue,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        Page<Course> courses;
        if (searchValue != null && !searchValue.isBlank()) {
            courses = courseService.getAllCoursesByName(page, size, searchValue, sortBy);
        } else {
            courses = courseService.getAllCourses(page, size, sortBy);
        }
        model.addAttribute("courses", courses);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("sortBy", sortBy);
        return "admin/course/course-list";
    }

    @GetMapping("/add")
    public String showAddForm(
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }
        model.addAttribute("courseAdd", new CourseDTO());
        return "admin/course/add-course";
    }

    @PostMapping("/add")
    public String addCourse(
            @Valid @ModelAttribute("courseAdd") CourseDTO courseAdd,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {
            return "admin/course/add-course";
        }

        if (courseService.existsByName(courseAdd.getName())) {
            result.rejectValue("name", "error.course", "Tên khóa học đã tồn tại!");
            return "admin/course/add-course";
        }

        String img = null;

        if (courseAdd.getFile() != null && !courseAdd.getFile().isEmpty()) {
            try {
                img = cloudinaryService.uploadImage(courseAdd.getFile());
            } catch (IOException e) {
                e.printStackTrace();
                result.rejectValue("file", "error.upload.image", "Có lỗi khi upload file: " + e.getMessage());
                return "admin/course/add-course";
            }
        }

        Course newCourse = Course.builder()
                .name(courseAdd.getName())
                .duration(courseAdd.getDuration())
                .instructor(courseAdd.getInstructor())
                .image(img)
                .build();

        try {
            courseService.save(newCourse);
            redirectAttributes.addFlashAttribute("successMsg", "Thêm khóa học thành công");
            return "redirect:/courses";
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("errMsg", "Có lỗi khi thêm khóa học, xin thử lại");
            return "admin/course/add-course";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable("id") Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        Optional<Course> courseOptional = courseService.findCourseById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            CourseDTO courseEdit = new CourseDTO();
            courseEdit.setId(id);
            courseEdit.setName(course.getName());
            courseEdit.setDuration(course.getDuration());
            courseEdit.setInstructor(course.getInstructor());
            model.addAttribute("courseEdit", courseEdit);
            return "admin/course/edit-course";
        }
        redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy id khóa học, xin thử lại");
        return "redirect:/courses";
    }

    @PostMapping("/update")
    public String updateCourse(
            @Valid @ModelAttribute("courseEdit") CourseDTO courseEdit,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {
            return "admin/course/edit-course";
        }

        Optional<Course> courseOptional = courseService.findCourseById(courseEdit.getId());
        if (courseOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy id khóa học, xin thử lại");
            return "redirect:/courses";
        }

        if (!courseOptional.get().getName().equals(courseEdit.getName()) &&
                courseService.existsByName(courseEdit.getName())
        ) {
            result.rejectValue("name", "error.course", "Tên khóa học đã tồn tại!");
            return "admin/course/edit-course";
        }

        String img = courseOptional.get().getImage();

        if (courseEdit.getFile() != null && !courseEdit.getFile().isEmpty()) {
            try {
                img = cloudinaryService.uploadImage(courseEdit.getFile());
            } catch (IOException e) {
                e.printStackTrace();
                result.rejectValue("file", "error.upload.image", "Có lỗi khi upload file: " + e.getMessage());
                return "admin/course/edit-course";
            }
        }

        Course updatedCourse = Course.builder()
                .id(courseEdit.getId())
                .name(courseEdit.getName())
                .duration(courseEdit.getDuration())
                .instructor(courseEdit.getInstructor())
                .image(img)
                .createAt(courseOptional.get().getCreateAt())
                .build();

        try {
            courseService.save(updatedCourse);
            redirectAttributes.addFlashAttribute("successMsg", "Cập nhật thông tin khóa học thành công");
            return "redirect:/courses";
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("errMsg", "Có lỗi khi cập nhật thông tin khóa học, xin thử lại");
            return "admin/course/edit-course";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        if (courseService.deleteCourseById(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa khóa học thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy id khóa học, xin thử lại");
        }
        return "redirect:/courses";
    }
}

package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.ChangePasswordDTO;
import ra.edu.model.dto.UserUpdate;
import ra.edu.model.entity.User;
import ra.edu.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showStudentPage() {
        return "student";
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
        return "edit-student";
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
            model.addAttribute("studentUpdate", studentUpdate);
            return "edit-student";
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
            model.addAttribute("studentUpdate", studentUpdate);
            return "edit-student";
        }

        if (!loggedUser.getPhone().equals(studentUpdate.getPhone()) &&
                userService.isExistPhone(studentUpdate.getPhone())
        ) {
            model.addAttribute("studentUpdate", studentUpdate);
            bindingResult.rejectValue("phone", "err.phone", "Số điện thoại đã tồn tại");
            return "edit-student";
        }

        Optional<User> userOpt = userService.findById(loggedUser.getId());
        if (userOpt.isEmpty()) {
            model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id tài khoản, xin thử lại");
            model.addAttribute("studentUpdate", studentUpdate);
            return "edit-student";
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
            return "edit-student";
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
        return "edit-password";
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
            return "edit-password";
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Phiên đăng nhập đã hết, xin mời đăng nhập lại");
            return "redirect:/users/login";
        }

        if (!BCrypt.checkpw(changePasswordDTO.getCurrentPassword(), loggedUser.getPassword())) {
            bindingResult.rejectValue("currentPassword", "err.currentPassword", "Mật khẩu hiện tại không đúng");
            return "edit-password";
        };

        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            bindingResult.rejectValue("confirmNewPassword", "err.confirmNewPassword", "Mật khẩu xác nhận không trùng khớp");
            return "edit-password";
        };

        Optional<User> userOpt = userService.findById(loggedUser.getId());
        if (userOpt.isEmpty()) {
            model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id tài khoản, xin thử lại");
            return "edit-password";
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
            return "edit-password";
        }
    }
}

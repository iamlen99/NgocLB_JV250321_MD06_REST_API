package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.DoctorDTO;
import ra.edu.model.entity.Doctor;
import ra.edu.service.DoctorService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctorController")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String showDoctorList(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/doctorList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctorDTO", new DoctorDTO());
        return "doctor/addDoctor";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
                               Model model) {
        Optional<Doctor> doctorOptional = doctorService.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setFullName(doctor.getFullName());
            doctorDTO.setSpecialization(doctor.getSpecialization());
            doctorDTO.setContact(doctor.getContact());
            doctorDTO.setPhone(doctor.getPhone());
            model.addAttribute("doctorDTO", doctorDTO);
            return "doctor/editDoctor";
        }
        model.addAttribute("errMsg", "Có lỗi khi lấy id bác sĩ");
        return "doctor/doctorList";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id,
                               RedirectAttributes redirectAttributes) {
        Optional<Doctor> doctorOptional = doctorService.findById(id);
        if (doctorOptional.isPresent()) {
            doctorService.deleteDoctor(doctorOptional.get());
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy id bác sĩ");
        }
        return "redirect:/doctorController";
    }

    @GetMapping("/change-status/{id}")
    public String changeStatus(@PathVariable("id") Long id) {
        doctorService.changeStatus(id);
        return "redirect:/doctorController";
    }

    @PostMapping("/add")
    public String addDoctor(@Valid @ModelAttribute("doctorDTO") DoctorDTO doctorDTO,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctor/addDoctor";
        }

        if (doctorService.findByPhone(doctorDTO.getPhone()).isPresent()) {
            result.rejectValue("phone", "error.phone", "Số điện thoại đã tồn tại!");
            return "doctor/addDoctor";
        }
        doctorService.addDoctor(doctorDTO);
        redirectAttributes.addFlashAttribute("successMsg", "Thêm bác sĩ thành công!");
        return "redirect:/doctorController";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@Valid @ModelAttribute("doctorDTO") DoctorDTO doctorDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctor/editDoctor";
        }
        if (doctorService.findByPhone(doctorDTO.getPhone()).isPresent()
                && doctorService.findByPhone(doctorDTO.getPhone()).get().getId() != doctorDTO.getId()) {
            result.rejectValue("phone", "error.phone", "Số điện thoại đã tồn tại!");
            return "doctor/editDoctor";
        }
        doctorService.updateDoctor(doctorDTO);
        redirectAttributes.addFlashAttribute("successMsg", "Thay đổi thông tin bác sĩ thành công!");
        return "redirect:/doctorController";
    }
}

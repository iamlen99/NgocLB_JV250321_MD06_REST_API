package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Patient;
import ra.edu.service.PatientService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patientController")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public String showPatientList(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient/patientList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/addPatient";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
                               Model model) {
        Optional<Patient> patientOptional = patientService.findPatientById(id);
        if (patientOptional.isPresent()) {
            model.addAttribute("patient", patientOptional.get());
            return "patient/editPatient";
        }
        model.addAttribute("errMsg", "Có lỗi khi lấy id bệnh nhân");
        return "patient/patientList";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id,
                               RedirectAttributes redirectAttributes) {
        Optional<Patient> patientOptional = patientService.findPatientById(id);
        if (patientOptional.isPresent()) {
            if(patientService.deletePatient(patientOptional.get())){
                redirectAttributes.addFlashAttribute("successMsg", "Xóa bệnh nhân thành công");
            } else{
                redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình xóa bệnh nhân");
            }
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy id bệnh nhân");
        }
        return "redirect:/patientController";
    }

    @GetMapping("/change-status/{id}")
    public String changeStatus(@PathVariable("id") Long id) {
        patientService.changeStatus(id);
        return "redirect:/patientController";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient/addPatient";
        }

        if (patientService.findPatientByPhone(patient.getPhone()).isPresent()) {
            result.rejectValue("phone", "error.phone", "Số điện thoại đã tồn tại!");
            return "patient/addPatient";
        }
        if(patientService.addPatient(patient)){
            redirectAttributes.addFlashAttribute("successMsg", "Thêm bệnh nhân thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình thêm bệnh nhân!");
        }
        return "redirect:/patientController";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@Valid @ModelAttribute("patient") Patient patient,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient/editPatient";
        }
        if (patientService.findPatientByPhone(patient.getPhone()).isPresent()
                && patientService.findPatientByPhone(patient.getPhone()).get().getId() != patient.getId()) {
            result.rejectValue("phone", "error.phone", "Số điện thoại đã tồn tại!");
            return "patient/editPatient";
        }
        if(patientService.updatePatient(patient)){
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi thông tin bệnh nhân thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình sửa thông tin bệnh nhân!");
        }
        return "redirect:/patientController";
    }
}

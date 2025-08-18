package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Appointment;
import ra.edu.service.AppointmentService;
import ra.edu.service.DoctorService;
import ra.edu.service.PatientService;

import java.util.List;

@Controller
@RequestMapping("/appointmentController")
public class AppointmentController {
    private static final int pageSize = 5;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @GetMapping
    public String appointmentList(@RequestParam(value = "page",required = false, defaultValue = "1")  int page,
            Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments(page, pageSize);
        int totalPages = appointmentService.getTotalPages(pageSize);
        model.addAttribute("totalPages", totalPages);
        System.out.println(totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("appointments", appointments);
        return "appointment/appointmentList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorService.getCurrentDoctors());
        model.addAttribute("patients", patientService.getCurrentPatients());
        return "appointment/addAppointment";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id,
                                    RedirectAttributes redirectAttributes) {
        if (appointmentService.deleteAppointment(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Hủy lịch khám thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình hủy lịch khám");
        }
        return "redirect:/appointmentController";
    }

    @PostMapping("/add")
    public String addAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("doctors", doctorService.getCurrentDoctors());
            model.addAttribute("patients", patientService.getCurrentPatients());
            return "appointment/addAppointment";
        }

        if (appointmentService.findByTime(appointment.getDoctor().getId(), appointment.getDate(), appointment.getHour()).isPresent()) {
            model.addAttribute("errMsg", "Bác sĩ bạn chọn đã có lịch khám giờ này rồi");
            model.addAttribute("doctors", doctorService.getCurrentDoctors());
            model.addAttribute("patients", patientService.getCurrentPatients());
            return "appointment/addAppointment";
        }
        if (appointmentService.addAppointment(appointment)) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm lịch khám thành công");
            return "redirect:/appointmentController";
        }
        model.addAttribute("errMsg", "Có lỗi trong quá trình thêm lịch khám");
        return "appointment/addAppointment";
    }
}

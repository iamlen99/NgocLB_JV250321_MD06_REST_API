package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Service;
import ra.edu.service.AnimalService;
import ra.edu.service.ServiceFunction;
import ra.edu.storage.CloudinaryService;

@Controller
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceFunction serviceFunction;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String showServices(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size,
                               Model model) {
        Page<Service> services = serviceFunction.getAllServices(page, size);
        model.addAttribute("services", services);
        return "listServices";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("service", new Service());
        model.addAttribute("animals", animalService.findAll());
        return "addService";
    }

    @PostMapping("/add")
    public String addService(@Valid @ModelAttribute("service") Service service,
                             BindingResult result,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("animals", animalService.findAll());
            return "addService";
        }

        if (file == null || file.isEmpty()) {
            model.addAttribute("animals", animalService.findAll());
            model.addAttribute("errImg", "Vui lòng chọn ảnh dịch vụ");
            return "addService";
        }

        try {
            service.setImage(cloudinaryService.uploadImage(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Service savedService = serviceFunction.saveService(service);
        if (savedService != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm dịch vụ thành công");
            return "redirect:/services";
        }
        model.addAttribute("animals", animalService.findAll());
        model.addAttribute("errMsg", "Thêm dịch vụ thất bại, vui lòng thử lại!");
        return "addService";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            Model model) {
        Service service = serviceFunction.getServiceById(id);
        if (service == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id");
            return "redirect:/services";
        }
        model.addAttribute("service", service);
        model.addAttribute("animals", animalService.findAll());
        return "editService";
    }

    @PostMapping("/edit")
    public String editService(@Valid @ModelAttribute("service") Service service,
                             BindingResult result,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("animals", animalService.findAll());
            return "editService";
        }

        service.setImage(serviceFunction.getServiceById(service.getId()).getImage());

        if (file != null && !file.isEmpty()) {
            try {
                service.setImage(cloudinaryService.uploadImage(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Service savedService = serviceFunction.saveService(service);
        if (savedService != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa dịch vụ thành công");
            return "redirect:/services";
        }
        model.addAttribute("animals", animalService.findAll());
        model.addAttribute("errMsg", "Sửa dịch vụ thất bại, vui lòng thử lại!");
        return "editService";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        if (serviceFunction.deleteServiceById(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Dịch vụ đã được xóa thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Xóa dịch vụ thất bại, vui lòng thử lại");
        }
        return "redirect:/services";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes,
                              Model model) {
        Service service = serviceFunction.getServiceById(id);
        if (service == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id");
            return "redirect:/services";
        }
        model.addAttribute("service", service);
        return "serviceDetail";
    }
}

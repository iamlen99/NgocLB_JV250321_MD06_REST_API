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
import ra.edu.model.entity.Animal;
import ra.edu.model.entity.Service;
import ra.edu.service.AnimalService;
import ra.edu.service.ServiceFunction;
import ra.edu.storage.CloudinaryService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ServiceFunction serviceFunction;
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public String showServices(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size,
                               @RequestParam(name = "searchNameService", required = false, defaultValue = "") String searchName,
                               @RequestParam(name = "searchAnimalId", required = false)  Long searchAnimalId,
                               Model model) {
        Page<Service> services;
        if (searchAnimalId != null) {
            services = serviceFunction.getAllServices(page, size, searchName, searchAnimalId);
            System.out.println(searchAnimalId);
        } else {
            services = serviceFunction.getAllServices(page, size, searchName);
        }

        List<Animal> animals = animalService.findAll();
        model.addAttribute("animals", animals);
        model.addAttribute("services", services);
        model.addAttribute("searchName", searchName);
        model.addAttribute("currentAnimalId", searchAnimalId);
        return "home";
    }
}

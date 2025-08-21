package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Animal;
import ra.edu.service.AnimalService;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public String showAnimals(Model model) {
        model.addAttribute("animals", animalService.findAll());
        return "listAnimals";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "addAnimal";
    }

    @GetMapping("/delete")
    public String deleteAnimal(@RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {
        if(animalService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Thú cưng đã được xóa thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Xóa thú cưng thất bại, vui lòng thử lại");
        }
        return "redirect:/animals";
    }

    @PostMapping("/add")
    public String addAnimal(@Valid @ModelAttribute("animal") Animal animal,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            return "addAnimal";
        }
        Animal savedAnimal = animalService.save(animal);
        if (savedAnimal != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm thú cưng thành công");
            return "redirect:/animals";
        }
        model.addAttribute("errMsg", "Thêm thú cưng thất bại, vui lòng thử lại!");
        return "addAnimal";
    }
}

package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.exercise.UserProfile;
import ra.exercise.model.uploadfile.Upload;
import ra.exercise.service.storage.CloudinaryService;
import ra.exercise.service.storage.SimpleStorage;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProfileController {
    @GetMapping
    public String user(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "uploadForm";
    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file,@ModelAttribute("userProfile") UserProfile userProfile, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            return "uploadForm";
        }

        SimpleStorage cloudinary = new CloudinaryService(
                "dwfphppwr",
                "871545392476297",
                "LjQnJtC9G1LCMb1LM-wvpML4lmg"
        );

        Upload uploadModel = new Upload(cloudinary);
        try {
            String imgUrl = uploadModel.execute(file.getBytes());
            if (imgUrl == null) {
                model.addAttribute("errorMessage", "Upload file thất bại, vui lòng thử lại.");
                return "uploadForm";
            }
            model.addAttribute("imgUrl", imgUrl);
            model.addAttribute("userProfile", userProfile);
            return "profileResult";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

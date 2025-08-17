package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.PostDTO;
import ra.edu.model.entity.Post;
import ra.edu.model.entity.User;
import ra.edu.service.PostService;

import java.util.Optional;

@Controller
@RequestMapping("/postController")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public String index(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/userController/login";
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("posts", postService.findAllPosts(currentUser.getId()));
        return "ex03/postList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "ex04/addPost";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {
        Optional<Post> post = postService.findPostById(id);
        if (post.isPresent()) {
            postService.deletePost(post.get());
            redirectAttributes.addFlashAttribute("successMsg", "Xóa bài viết thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id bài viết");
        }
        return "redirect:/postController";
    }

    @PostMapping("/add")
    public String addPost(@Valid @ModelAttribute("postDTO") PostDTO postDTO, BindingResult result,
                          RedirectAttributes redirectAttributes,
                          HttpSession session) {
        if(result.hasErrors()) {
            return "ex04/addPost";
        }
        postService.addPost(postDTO, session);
        redirectAttributes.addFlashAttribute("successMsg", "Thêm mới bài viết thành công!");
        return "redirect:/postController";
    }
}

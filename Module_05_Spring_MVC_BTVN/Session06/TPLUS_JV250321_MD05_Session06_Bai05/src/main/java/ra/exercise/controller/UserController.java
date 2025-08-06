package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.exercise.model.entity.User;
import ra.exercise.model.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/users"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView showAllUsers() {
        List<User> userList = userService.getAllUsers();
        ModelAndView mv = new ModelAndView("userList");
        mv.addObject("userList", userList);
        return mv;
    }
}

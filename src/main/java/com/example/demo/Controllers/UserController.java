package com.example.demo.Controllers;

import com.example.demo.models.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class UserController {

    private BookService bookService = new BookService();
    private UserService userService = new UserService();

    @PostMapping(path = "/login")
    public String doLogin(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        @ModelAttribute("user") User user,
                        Model model) {
        user.setName(name);
        user.setRegistered(true);
        userService.registerNewUser(name, password, true);
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @RequestMapping("/SignIn")
    public String signIn() {
        return "SignIn";
    }

    @RequestMapping("/Logout")
    public String logout(@ModelAttribute("user")User user, Model model){
        user.setRegistered(false);
        user.setName("Guest");
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }
}

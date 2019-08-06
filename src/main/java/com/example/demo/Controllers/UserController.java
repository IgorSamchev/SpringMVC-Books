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

    @PostMapping(path = "/addNewUserAndLogin")
    public String doRegistration(@RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @ModelAttribute("user") User user,
                                 Model model) {
        user.setName(name);
        user.setRegistered(true);
        if (userService.registerNewUser(name, password, true)) {
            model.addAttribute("books", bookService.findAllBooks());
            return "MainView";
        } else {
            model.addAttribute("Already_taken", true);
            return "registrationView";
        }

    }

    @PostMapping(path = "/login")
    public String doLogin(@RequestParam("name") String name,
                          @RequestParam("password") String password,
                          @ModelAttribute("user") User user,
                          Model model) {
        if (userService.checkNameAndPassword(name, password)){
            user.setName(name);
            user.setRegistered(true);
            model.addAttribute("books", bookService.findAllBooks());
            return "MainView";
        }
        model.addAttribute("LoginError", true);
        return "SignIn";
    }

    @RequestMapping("/SignIn")
    public String signIn() {
        return "SignIn";
    }

    @RequestMapping("/registration")
    public String registration() {
        return "registrationView";
    }

    @RequestMapping("/Logout")
    public String logout(@ModelAttribute("user") User user, Model model) {
        user.setRegistered(false);
        user.setName("Guest");
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
       User user = new User();
       user.setRegistered(false);
       return user;
    }
}

package com.example.demo.Controllers;

import com.example.demo.models.User;
import com.example.demo.services.BookService;
import com.example.demo.services.LoggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class MainController {
    private BookService bookService = new BookService();
    private LoggerService loggerService = new LoggerService();

    @ModelAttribute("user")
    public User setUpUserForm() {
        User user = new User();
        user.setRegistered(false);
        return user;
    }

    @RequestMapping("/")
    public String hello() {
        loggerService.getIP();
        return "index";
    }

    @RequestMapping("/books_list")
    public String booksList(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }
}



package com.example.demo.Controllers;

import com.example.demo.services.BookReset;
import com.example.demo.services.BookService;
import com.example.demo.services.Gmail;
import com.example.demo.services.LoggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServiceController {
    private BookService bookService = new BookService();
    private LoggerService loggerService = new LoggerService();

    @RequestMapping("/reset87")
    public String reset(Model model) {
        new BookReset().reset();
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log(Model model) {
        model.addAttribute("logs", loggerService.getLogs());
        return "log";
    }

    @PostMapping(path = "/about/sendEmail/")
    public String mail(@RequestParam("name") String name,
                       @RequestParam("subject") String subject,
                       @RequestParam("text") String text,
                       @RequestParam("g-recaptcha-response") String reCaptcha) {
        if (reCaptcha.length() > 10)
            Gmail.sendMail(name, subject, text);
        return "about";
    }
}

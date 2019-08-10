package com.example.demo.Controllers;

import com.example.demo.models.User;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class DeleteBookController {
    private BookService bookService = new BookService();

    @RequestMapping(value = "/books/doDelete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id,
                             @ModelAttribute("user") User user,
                             Model model) {
        if (user.isRegistered()) {
            bookService.deleteBook(bookService.findBook(id));
            model.addAttribute("books", bookService.findAllBooks());
            return "MainView";
        } else {
            return "SignIn";
        }
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        User user = new User();
        user.setRegistered(false);
        return user;
    }
}

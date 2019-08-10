package com.example.demo.Controllers;

import com.example.demo.models.Book;
import com.example.demo.models.User;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class AddBookController {
    private BookService bookService = new BookService();

    @RequestMapping(value = "/books/addBook", method = RequestMethod.GET)
    public String showCreateForm() {
        return "AddView";
    }

    @PostMapping(path = "/books/addBook/newBook")
    public String addNewBook(@RequestParam("AddedBookTitle") String title,
                             @RequestParam("AddedBookAuthor") String author,
                             @RequestParam("AddedBookISBN") String isbn,
                             @RequestParam("g-recaptcha-response") String reCaptcha,
                             @ModelAttribute("user") User user,
                             Model model) {
        if (user.isRegistered()) {
            if (reCaptcha.length() > 10) {
                bookService.saveBook(new Book(title, author, isbn, null));
                model.addAttribute("books", bookService.findAllBooks());
                return "MainView";
            } else {
                return "AddView";
            }
        } else return "SignIn";
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        User user = new User();
        user.setRegistered(false);
        return user;
    }
}

package com.example.demo.Controllers;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
                             Model model) {
        if (reCaptcha.length() > 10) {
            bookService.saveBook(new Book(title, author, isbn, null));
            model.addAttribute("books", bookService.findAllBooks());
            return "MainView";
        } else {
            return "AddView";
        }
    }
}

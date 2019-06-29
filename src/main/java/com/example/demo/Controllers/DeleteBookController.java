package com.example.demo.Controllers;

import com.example.demo.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DeleteBookController {
    private BookService bookService = new BookService();

    @RequestMapping(value = "/books/doDelete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id, Model model) {
        bookService.deleteBook(bookService.findBook(id));
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }
}

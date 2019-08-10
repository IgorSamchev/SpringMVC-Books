package com.example.demo.Controllers;

import com.example.demo.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditBookAndCommentsController {
    private BookService bookService = new BookService();

    @RequestMapping("/books_edit/{id}")
    public String booksEditView(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findBook(id));
        return "EditView";
    }

    @RequestMapping(value = "/edit_book/{request}", method = RequestMethod.GET)
    public String bookEdit(@PathVariable String request, Model model) {
        bookService.updateBook(request);
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@RequestParam("id") String idString,
                             @RequestParam("bigComment") String comment,
                             Model model) {
        int id = Integer.parseInt(idString);
        if (comment.trim().length() > 0)
        bookService.addComment(id, comment);
        model.addAttribute("books", bookService.findAllBooks());
        return booksEditView(id, model);
    }
}

package com.example.demo;

import com.example.demo.models.Book;
import com.example.demo.services.BookReset;
import com.example.demo.services.BookService;
import com.example.demo.services.Gmail;
import com.example.demo.services.LoggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private BookService bookService = new BookService();
    private LoggerService loggerService = new LoggerService();

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @RequestMapping("/books_list")
    public String booksList(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @RequestMapping(value = "/books/doDelete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id, Model model) {
        bookService.deleteBook(bookService.findBook(id));
        model.addAttribute("books", bookService.findAllBooks());
        return "MainView";
    }

    @RequestMapping(value = "/books/addBook", method = RequestMethod.GET)
    public String showCreateForm() {
        return "AddView";
    }

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

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = "edit_book/about", method = RequestMethod.GET)
    public String about2() {
        return "about";
    }

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

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@RequestParam("id") String idString,
                             @RequestParam("bigComment") String comment,
                             Model model) {
        int id = Integer.parseInt(idString);
        bookService.addComment(id, comment);
        model.addAttribute("books", bookService.findAllBooks());
        return booksEditView(id, model);
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



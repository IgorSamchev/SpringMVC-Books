package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class AppController {

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @RequestMapping("/books_list")
    public String booksList(Model model) {
        Logger.getIP();
        DataBase business = new DataBase();
        model.addAttribute("books", business.getBooksList());
        return "MainView";
    }

    @RequestMapping(value = "/books/doDelete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id, Model model) {
        Logger.deleteBook(id);
        Book.deleteBookFromDataBase(id);
        DataBase business = new DataBase();
        model.addAttribute("books", business.getBooksList());
        return "MainView";
    }

    @RequestMapping(value = "/books/addBook", method = RequestMethod.GET)
    public String showCreateForm() {
        return "AddView";
    }

    @RequestMapping("/books_edit/{id}")
    public String booksEditView(@PathVariable Long id, Model model) {
        DataBase business = new DataBase();
        Book b = Book.findBookByID(id, business.getBooksList());
        model.addAttribute("book", b);
        return "EditView";
    }

    @RequestMapping(value = "/edit_book/{request}", method = RequestMethod.GET)
    public String bookEdit(@PathVariable String request, Model model) {
        Book.update(request);
        DataBase business = new DataBase();
        model.addAttribute("books", business.getBooksList());
        return "MainView";
    }

    @RequestMapping(value = "/add_Comment/{request}", method = RequestMethod.GET)
    public String addComment(@PathVariable String request, Model model) {
        long id = Long.parseLong(request.substring(0, request.indexOf("c")));
        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        Book b = Book.findBookByID(id, booksList);
        Book.addNewComment(Objects.requireNonNull(b), request);
        model.addAttribute("books", booksList);
        return booksEditView(id, model);
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
        DataBase.reset();
        DataBase business = new DataBase();
        model.addAttribute("books", business.getBooksList());
        return "MainView";
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log(Model model) {
        List<Logger> logs = DataBase.getLogs();
        model.addAttribute("logs", logs);
        return "log";
    }

    @PostMapping(path = "/books/addBook/newBook")
    public String Whispers(@RequestParam("AddedBookTitle") String title,
                           @RequestParam("AddedBookAuthor") String author,
                           @RequestParam("AddedBookISBN") String isbn,
                           @RequestParam("g-recaptcha-response") String reCaptcha,
                           Model model) {
        if (reCaptcha.length() > 10) {
            Book.addNewBook(title, author, isbn);
            DataBase business = new DataBase();
            model.addAttribute("books", business.getBooksList());
            return "MainView";
        } else {
            return "AddView";
        }
    }

    @PostMapping(path = "/about/sendEmail/")
    public String mail(@RequestParam("name") String name,
                       @RequestParam("subject") String subject,
                       @RequestParam("text") String text,
                       @RequestParam("g-recaptcha-response") String reCaptcha){
        if (reCaptcha.length() > 10)
            Gmail.sendMail(name, subject, text);
        return "about";
    }
}



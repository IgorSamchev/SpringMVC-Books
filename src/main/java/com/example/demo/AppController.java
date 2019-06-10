package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

@Controller
public class AppController {

    @RequestMapping(value = "/books/changeCurrentLanguageEN")
    public String changeLanguageEn(Model model) {
        Language.setCurrentLanguageEN();
        booksList(model);
        return "MainView";
    }

    @RequestMapping(value = "/books/changeCurrentLanguageEE")
    public String changeLanguageEe(Model model) {
        Language.setCurrentLanguageEE();
        booksList(model);
        return "MainView";
    }

    @RequestMapping(value = "/books/changeCurrentLanguageRU")
    public String changeLanguageRu(Model model) {
        Language.setCurrentLanguageRU();
        booksList(model);
        return "MainView";
    }


    @RequestMapping("/books_list")
    public String booksList(Model model) {
        Logger.getIP();
        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);
        model.addAttribute("Title", Language.getCurrentLanguage().equals("en") ? "Title" : Language.getCurrentLanguage().equals("ee") ? "Pealkiri" : "Наименование");
        model.addAttribute("Author", Language.getCurrentLanguage().equals("en") ? "Author" : Language.getCurrentLanguage().equals("ee") ? "Autor" : "Автор");
        model.addAttribute("Comments", Language.getCurrentLanguage().equals("en") ? "Comments" : Language.getCurrentLanguage().equals("ee") ? "Kommentaarid" : "Комметарии");
        model.addAttribute("Action", Language.getCurrentLanguage().equals("en") ? "Action" : Language.getCurrentLanguage().equals("ee") ? "Tegu" : "Действие");
        model.addAttribute("currentLanguage", Language.getCurrentLanguage());
        return "MainView";
    }

    @RequestMapping(value = "books/doDelete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id, Model model) {
        Logger.deleteBook(id);
        Book.deleteBookFromDataBase(id);
        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);
        model.addAttribute("currentLanguage", Language.getCurrentLanguage());
        return "MainView";
    }

    @RequestMapping(value = "books/addBook", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("currentLanguage", Language.getCurrentLanguage());
        return "AddView";
    }

    @RequestMapping(value = "books/addBook/{submit}", method = RequestMethod.GET)
    public String addNewBook(@PathVariable String submit, Model model) {
        switch (submit) {
            case "changeCurrentLanguageEN":
                Language.setCurrentLanguageEN();
                model.addAttribute("currentLanguage", Language.getCurrentLanguage());
                return "AddView";
            case "changeCurrentLanguageEE":
                Language.setCurrentLanguageEE();
                model.addAttribute("currentLanguage", Language.getCurrentLanguage());
                return "AddView";
            case "changeCurrentLanguageRU":
                Language.setCurrentLanguageRU();
                model.addAttribute("currentLanguage", Language.getCurrentLanguage());
                return "AddView";
            default:
                Book.addNewBook(submit);
                DataBase business = new DataBase();
                List<Book> booksList = business.getBooksList();
                model.addAttribute("books", booksList);
                model.addAttribute("currentLanguage", Language.getCurrentLanguage());
                return "MainView";
        }
    }

    @RequestMapping("/books_edit/{id}")
    public String booksEditView(@PathVariable Long id, Model model) {
        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        Book b = Book.findBookByID(id, booksList);
        model.addAttribute("book", b);
        model.addAttribute("currentLanguage", Language.getCurrentLanguage());
        return "EditView";
    }

    @RequestMapping(value = "/edit_book/{request}", method = RequestMethod.GET)
    public String bookEdit(@PathVariable String request, Model model) {
        Book.update(request);

        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

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
    public String about(Model model) {
        model.addAttribute("currentLanguage", Language.getCurrentLanguage());
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
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

        return "MainView";
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log(Model model) {
        List<Logger> logs = DataBase.getLogs();
        model.addAttribute("logs", logs);
        model.addAttribute("Date", Language.getCurrentLanguage().equals("en") ? "Date" : Language.getCurrentLanguage().equals("ee") ? "Kuupäev" : "Дата");
        model.addAttribute("Time", Language.getCurrentLanguage().equals("en") ? "Time" : Language.getCurrentLanguage().equals("ee") ? "Aeg" : "Время");
        model.addAttribute("Message", Language.getCurrentLanguage().equals("en") ? "Message" : Language.getCurrentLanguage().equals("ee") ? "Teade" : "Сообщение");
        model.addAttribute("currentLanguage", Language.getCurrentLanguage());
        return "log";
    }
}



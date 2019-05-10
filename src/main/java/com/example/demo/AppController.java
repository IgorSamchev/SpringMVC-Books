package com.example.demo;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class AppController {


    @RequestMapping("/books_list")
    public String booksList(Model model) {
        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

        return "MainView";
    }

    @RequestMapping(value = "books/doDelete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id, Model model) {
        Book.deleteBookFromDataBase(id);

        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

        return "MainView";
    }

    @RequestMapping(value = "books/addBook", method = RequestMethod.GET)
    public String showCreateForm() {
        return "AddView";
    }

    @RequestMapping(value = "books/addBook/{submit}", method = RequestMethod.GET)
    public String addNewBook(@PathVariable String submit, Model model) {
        Book.addNewBook(submit);

        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

        return "MainView";
    }

    //просмотр
    @RequestMapping("/books_edit/{id}")
    public String booksEditView(@PathVariable Long id, Model model) {
        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        Book b = Book.findBookByID(id, booksList);
        model.addAttribute("book", b);
        return "EditView";
    }

    //изменение
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
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

        return "MainView";
    }

}

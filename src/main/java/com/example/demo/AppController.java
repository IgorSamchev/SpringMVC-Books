package com.example.demo;

import java.util.List;

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
            System.out.println(submit);

        DataBase business = new DataBase();
        List<Book> booksList = business.getBooksList();
        model.addAttribute("books", booksList);

        return "MainView";
    }



}

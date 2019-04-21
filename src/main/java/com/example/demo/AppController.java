package com.example.demo;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class AppController {



    @RequestMapping("/books_list")
    public String booksList(Model model) {
        DataBaseReader business = new DataBaseReader();
        List<Book> booksList = business.getBooksList();

        model.addAttribute("books", booksList);
        return "contact";
    }
}

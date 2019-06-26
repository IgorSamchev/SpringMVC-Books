package com.example.demo.services;

import com.example.demo.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookReset {
    public static void reset() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("React Quickly", "Azat Mardan", "978-1617293344", null));
        list.add(new Book("Effective Java 3rd Edition", "Joshua Bloch", "978-0134685991", null));
        list.add(new Book("Head First Design Patterns", "Eric Freeman", "978-0596007126", null));
        list.add(new Book("Elements of Programming", "Adnan Aziz", "978-1517671273", null));
        list.add(new Book("Storage and Analysis", "Tom White", "978-1491901632", null));
        list.add(new Book("Oracle Certified Associate", "Jeanne Boyarsky", "978-1118957400", null));
        list.add(new Book("Core Java Volume I", "Cay S. Horstmann", "978-0135166307", null));
        list.add(new Book("Modern Java in Action", "Azat Mardan", "978-1617293346", null));
        list.add(new Book("React Quickly", "Raoul-Gabriel Urma", "978-1617293340", null));
        list.add(new Book("Java All-in-One", "Doug Lowe", "978-1119247791", null));
        list.add(new Book("Introduction to Java", "Y. Daniel Liang", "978-0134670942", null));
        list.add(new Book("Python for Programmers", "Paul J. Deitel", "978-0135224335", null));
        list.add(new Book("Mastering Java", "Michael B. White", "978-1792070112", null));
        list.add(new Book("Java in 24 Hours", "Rogers Cadenhead", " 978-0672337949", null));
        list.add(new Book("Regular Expression", "Tony Stubblebine", "978-0596514273", null));
        list.add(new Book("Robot Programming", "Cameron Hughes", "978-0789755001", null));
        list.add(new Book("Cloud Native Patterns", "Cornelia Davis ", "978-1617294297", null));

        BookService bookService = new BookService();
        for (Book book : list) {
            try {
                bookService.saveBook(book);
            } catch (Exception ignored) {
            }

        }
    }
}

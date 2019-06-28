package com.example.demo.services;

import com.example.demo.Utils.Utils;
import com.example.demo.dao.BookDao;
import com.example.demo.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();
    private LoggerService loggerService = new LoggerService();


    public BookService() {
    }

    public Book findBook(int id) {
        return bookDao.findById(id);
    }

    public void saveBook(Book book) {
        if (checkIsbnForDuplicates(book)) {
            loggerService.addNewBook(book.getTitle(), book.getAuthor(), book.getIsbn());
            bookDao.save(book);
        }
    }

    public void deleteBook(Book book) {
        loggerService.deleteBook(book.getId());
        bookDao.delete(book);
    }

    public void updateBook(String request) {
        String[] id = request.split("AddedBookTitle");
        Book book = new BookService().findBook(Integer.parseInt(id[0]));
        String[] data = new Utils().splitRequestForEditBookPage(request);
        book.setTitle(data[0]);
        book.setAuthor(data[1]);
        book.setIsbn(data[2]);
        loggerService.editBook(id[0], data);
        bookDao.update(book);
    }

    public List<Book> findAllBooks() {
        loggerService.getIP();
        return bookDao.findAll();
    }

    public void addComment(int id, String request) {
        Book book = findBook(id);
        String newComment = new Utils().newCommentRequestParser(book, request);
        book.setComment(newComment);
        loggerService.addComment(book.getId(), newComment);
        bookDao.update(book);
    }

    static boolean checkIsbnForDuplicates(Book book) {
        BookService bookService = new BookService();
        List<String> isbnList = new ArrayList<>();
        for (Book b : bookService.findAllBooks()) isbnList.add(b.getIsbn());
        return (!isbnList.contains(book.getIsbn()));
    }
}

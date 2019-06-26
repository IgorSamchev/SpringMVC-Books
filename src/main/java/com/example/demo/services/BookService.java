package com.example.demo.services;

import com.example.demo.Logger;
import com.example.demo.Utils.Utils;
import com.example.demo.dao.BookDao;
import com.example.demo.models.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public BookService() {
    }

    public Book findBook(int id) {
        return bookDao.findById(id);
    }

    public void saveBook(Book book) {
        Logger.addNewBook(book.getTitle(), book.getAuthor(), book.getIsbn());
        bookDao.save(book);
    }

    public void deleteBook(Book book) {
        Logger.deleteBook(book.getId());
        bookDao.delete(book);
    }

    public void updateBook(String request) {
        String[] id = request.split("AddedBookTitle");
        Book book = new BookService().findBook(Integer.parseInt(id[0]));
        String[] data = new Utils().splitRequestForEditBookPage(request);
        book.setTitle(data[0]);
        book.setAuthor(data[1]);
        book.setIsbn(data[2]);
        Logger.editBook(id[0], data);
        bookDao.update(book);
    }

    public List<Book> findAllBooks() {
        Logger.getIP();
        return bookDao.findAll();
    }

    public void addComment(int id, String request) {
        Book book = findBook(id);
        String newComment = new Utils().newCommentRequestParser(book, request);
        book.setComment(newComment);
        Logger.addComment(book.getId(), newComment);
        bookDao.update(book);
    }

}

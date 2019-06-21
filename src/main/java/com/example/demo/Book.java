package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Book {
    private int ID;
    private String Title;
    private String Author;
    private String ISBN;
    private List<String> comment;

    public Book(String title, String author, String ISBN, List<String> comment) {
        Title = title;
        Author = author;
        this.ISBN = ISBN;
        this.comment = comment;
    }

    public Book(int ID, String title, String author, String ISBN, String[] comment) {
        this.ID = ID;
        Title = title;
        Author = author;
        this.ISBN = ISBN;
        if (comment == null) {
            this.comment = Collections.emptyList();
        } else
            this.comment = Arrays.asList(comment);
    }

    static Book findBookByID(Long id, List<Book> booksList) {
        for (Book b : booksList) {
            if (b.getID() == id) return b;
        }
        return null;
    }

    static void addNewBook(String title, String author, String isbn) {
        DataBase.addNewBook(title, author, isbn);
        Logger.addNewBook(title, author, isbn);
    }

    static void deleteBookFromDataBase(long id) {
        DataBase.deleteBookById(id);
    }

    static void update(String request) {
        String[] id = request.split("AddedBookTitle");
        String[] data = new Utils().splitRequestForEditBookPage(request);
        DataBase.updateBook(id[0], data);
        Logger.editBook(id[0], data);
    }

    static void addNewComment(Book b, String request) {
        String newComment = new Utils().newCommentRequestParser(b, request);
        DataBase.addComment(b.getID(), newComment);
        Logger.addComment(b.getID(), newComment);
    }


    public List<String> getComment() {
        return comment;
    }


    public int getCommentLength() {
        return comment != null ? comment.size() : 0;
    }


    public String getISBN() {
        return ISBN;
    }


    public int getID() {
        return ID;
    }


    public String getTitle() {
        return Title;
    }


    public String getAuthor() {
        return Author;
    }
}

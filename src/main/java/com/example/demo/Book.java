package com.example.demo;

import java.util.ArrayList;
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

    private static String[] splitData(String request) {
        String[] titleRequest = request.split("AddedBookAuthor=");
        String title = titleRequest[0].substring(titleRequest[0].indexOf("=")+1);
        String author = titleRequest[1].substring(0, titleRequest[1].indexOf("AddedBookISBN"));
        String ISBN = titleRequest[1].substring(titleRequest[1]
                .indexOf("AddedBookISBN="))
                .replace("AddedBookISBN=", "");
        return new String[]{title, author, ISBN};
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
        String[] data = splitData(request);
        DataBase.updateBook(id[0], data);
        Logger.editBook(id[0], data);
}

    static void addNewComment(Book b, String request) {
        String newComment = request.substring(request.indexOf("=") + 1);
        List<String> list = new ArrayList<>(b.comment);
        list.add(newComment);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("~@~");
        }
        String comment = sb.toString().substring(0, sb.length() - 3);
        comment = comment
                .replace("~sem~", ";")
                .replace("~sharp~", "#")
                .replace("~percent~", "%")
                .replace("~question~", "?")
                .replace("~slash~", "/")
                .replace("~backSlash~", "\\")
                .replace("~leftBracket~", "[")
                .replace("~rightBracket~", "]")
                .replace("~wallSlash~", "|");
        DataBase.addComment(b.getID(), comment);
        Logger.addComment(b.getID(), comment);
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

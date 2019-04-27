package com.example.demo;


import java.util.List;

public class Book {
    private int ID;
    private String Title;
    private String Author;
    private String ISBN;
    private String[] comment;

    Book(int ID, String title, String author, String ISBN, String[] comment) {
        this.ID = ID;
        Title = title;
        Author = author;
        this.ISBN = ISBN;
        this.comment = comment;
    }

    public Book(String title, String author, String ISBN) {
        Title = title;
        Author = author;
        this.ISBN = ISBN;
    }

    public Book() {
    }

    static Book findBookByID(Long id, List<Book> booksList) {
        for (Book b : booksList) {
            if (b.getID() == id) return b;
        }
        return null;
    }

    private static String[] splitData(String request) {
        String[] titleRequest = request.split("AddedBookAuthor=");
        String title = titleRequest[0].substring(titleRequest[0].indexOf("=") + 1);
        String author = titleRequest[1].substring(0, titleRequest[1].indexOf("AddedBookISBN"));
        String ISBN = titleRequest[1].substring(titleRequest[1]
                .indexOf("AddedBookISBN="))
                .replace("AddedBookISBN=", "");
        return new String[]{title, author, ISBN};
    }

    static void addNewBook(String request) {
        String[] data = splitData(request);

        DataBase.addNewBook(data[0], data[1], data[2]);
    }

    static void deleteBookFromDataBase(long id) {
        DataBase.deleteBookById(id);
    }

    static void update(String request) {
        String[] id = request.split("AddedBookTitle");
        String[] data = splitData(request);

        DataBase.updateBook(id[0], data);

    }

    public String[] getComment() {
        return comment;
    }


    public int getCommentLength() {
        return comment != null ? comment.length : 0;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

}

package com.example.demo;

public class Book {
    private int ID;
    private String Title;
    private String Author;
    private String ISBN;
    public String[] comment;

    public Book(int ID, String title, String author, String ISBN, String[] comment) {
        this.ID = ID;
        Title = title;
        Author = author;
        this.ISBN = ISBN;
        this.comment = comment;
    }

    public String[] getComment() {
        return comment;
    }

    public int getCommentLength() {
        return comment != null? comment.length : 0;
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
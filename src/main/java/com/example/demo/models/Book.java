package com.example.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String comment;

    public Book(String title, String author, String isbn, String comment) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.comment = comment;
    }

    public Book(int id, String title, String author, String isbn, String comment) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.comment = comment;
    }

    public Book() {
    }

    public List<String> getCommentArray() {
        if (comment != null) {
            String[] comments = comment.split("~@~");
            return Arrays.asList(comments);
        }
        return new ArrayList<>();
    }

    public String getComment(){
        if (comment == null) return "";
        else return comment;
    }

    public int getCommentLength() {
        if (comment == null) return 0;
        else {
            String[] temp = comment.split("~@~");
            return temp.length;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }
}

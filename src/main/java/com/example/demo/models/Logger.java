package com.example.demo.models;

import javax.persistence.*;
import javax.servlet.http.HttpServlet;

@Entity
@Table(name = "logs")
public class Logger extends HttpServlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String day;
    private String time;
    private String message;

    public Logger() {
    }

    public Logger(String date, String time, String message) {
        this.day = date;
        this.time = time;
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

}
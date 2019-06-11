package com.example.demo;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Logger extends HttpServlet {
    private String date;
    private String time;
    private String message;

    Logger(String date, String time, String message) {
        this.date = date;
        this.time = time;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    private static String getTimeStamp() {
        return ZonedDateTime.now(ZoneId.of("Europe/Helsinki"))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    static void addNewBook(String title, String author, String isbn) {
        DataBase.log(getTimeStamp(),
                checkIP() +
                        " Added new Book: "
                        + title + ", "
                        + author + ", "
                        + isbn);
    }

    private static String checkIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return request.getRemoteAddr();
    }

    static void getIP() {
        DataBase.log(getTimeStamp(), checkIP() + " logged in");
    }

    static void deleteBook(Long id) {
        DataBase.log(getTimeStamp(), checkIP() + " Deleted book with ID " + id);
    }
}
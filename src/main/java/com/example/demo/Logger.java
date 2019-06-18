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

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    Logger(String date, String time, String message) {
        this.date = date;
        this.time = time;
        this.message = message;
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

    static void editBook(String id, String[] data) {
        DataBase.log(getTimeStamp(),
                checkIP() +
                        " Edit Book with ID "
                        + id + ": to "
                        + data[0] + " - "
                        + data[1] + " - "
                        + data[2]);
    }

    static void addComment(int id, String comment) {
        if (comment.contains("~@~")){
            DataBase.log(getTimeStamp(),
                    checkIP() +
                            " Added new comment to Book with ID "
                            + id + ": "
                            + comment.substring(comment.lastIndexOf("~@~") + 3));
        }else {
            DataBase.log(getTimeStamp(),
                    checkIP() +
                            " Added new comment to Book with ID "
                            + id + ": "
                            + comment);
        }
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
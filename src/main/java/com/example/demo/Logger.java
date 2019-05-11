package com.example.demo;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

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
        String timeStamp = new Timestamp(System.currentTimeMillis()).toString();
        return timeStamp.substring(0, timeStamp.length() - 4);
    }

    static void addNewBook(String title, String author, String isbn) {
        DataBase.log(getTimeStamp(),
                " Added new Book: "
                        + title + ", "
                        + author + ", "
                        + isbn);
    }

    static void getIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        DataBase.log(getTimeStamp(), " " + ip + " logged in");
    }

    static void deleteBook(Long id) {
        DataBase.log(getTimeStamp(), " Deleted book with ID " + id);
    }
}
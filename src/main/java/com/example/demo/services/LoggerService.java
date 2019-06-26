package com.example.demo.services;

import com.example.demo.dao.LoggerDao;
import com.example.demo.models.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LoggerService {

    private LoggerDao loggerDao = new LoggerDao();

    public  List<Logger> getLogs(){
        return loggerDao.findAll();
    }

    private static String[] getTimeStamp() {
        return ZonedDateTime.now(ZoneId.of("Europe/Helsinki"))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")).split(" ");
    }

     void addNewBook(String title, String author, String isbn) {
        String[] date = getTimeStamp();
         loggerDao.save(new Logger(date[0], date[1],
                checkIP()
                        + "Added new Book: "
                        + title + ", "
                        + author + ", "
                        + isbn));
    }

     void editBook(String id, String[] data) {
        String[] date = getTimeStamp();
        loggerDao.save(new Logger(date[0], date[1],
                checkIP()
                        + " Edit Book with ID "
                        + id + ": to "
                        + data[0] + " - "
                        + data[1] + " - "
                        + data[2]));
    }

     void addComment(int id, String comment) {
        String[] date = getTimeStamp();
        if (comment.contains("~@~")) {
            loggerDao.save(new Logger(date[0], date[1],
                    checkIP() +
                            " Added new comment to Book with ID "
                            + id + ": "
                            + comment.substring(comment.lastIndexOf("~@~") + 3)));
        } else {
            loggerDao.save(new Logger(date[0], date[1],
                    checkIP() +
                            " Added new comment to Book with ID "
                            + id + ": "
                            + comment));
        }
    }

    private static String checkIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return request.getRemoteAddr();
    }

     void getIP() {
        String[] date = getTimeStamp();
         loggerDao.save(new Logger(date[0], date[1], checkIP() + " logged in"));
    }

     void deleteBook(int id) {
        String[] date = getTimeStamp();
        loggerDao.save(new Logger(date[0], date[1],
                checkIP()
                        + " Deleted book with ID " + id));
    }
}


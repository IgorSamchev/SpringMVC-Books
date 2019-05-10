package com.example.demo;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class Logger extends HttpServlet {

    static void getIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        DataBase.log(new Timestamp(System.currentTimeMillis()) + " " + ip + " logged in");
    }

    static void deleteBook(Long id) {
        DataBase.log(new Timestamp(System.currentTimeMillis()) + " Deleted book with ID " + id);
    }
}
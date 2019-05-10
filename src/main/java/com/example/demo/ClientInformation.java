package com.example.demo;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientInformation extends HttpServlet {

    public static void getIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        System.out.println(ip);
        try {
            FileWriter fw = new FileWriter("src/main/resources/static/log.log", true);
            fw.write("try");
            fw.write(ip + "\n");

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
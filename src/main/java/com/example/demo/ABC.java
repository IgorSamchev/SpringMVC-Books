package com.example.demo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ABC {
    public static void sendMail(String name, String email, String time, String phone, String people, String text) {
        final String username = "abc.bookzzz@gmail.com";
        final String password = "Intelinside5";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ABC-Books"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("artjom.skortsov@mail.ru")
            );
            message.setSubject("Order");
            message.setText(name + " order:"
                    + "\n" + text
            + "\n" + "Customers phone: " + phone
            + "\n" + "Customers emial: " + email
            + "\n" + "Customers number:" + people
            + "\n" + time);


            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}

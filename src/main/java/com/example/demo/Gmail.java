package com.example.demo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class Gmail {

    static void sendMail(String name, String subject, String text) {
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
                    InternetAddress.parse("igor.samchev@gmail.com")
            );
            message.setSubject(subject);
            message.setText(name + " Send:"
                    + "\n" + text);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


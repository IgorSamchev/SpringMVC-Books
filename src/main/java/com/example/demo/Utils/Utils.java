package com.example.demo.Utils;

import com.example.demo.models.Book;

public class Utils {
    public String[] splitRequestForEditBookPage(String request) {
        String[] titleRequest = request.split("AddedBookAuthor=");
        String title = titleRequest[0].substring(titleRequest[0].indexOf("=") + 1);
        String author = titleRequest[1].substring(0, titleRequest[1].indexOf("AddedBookISBN"));
        String ISBN = titleRequest[1].substring(titleRequest[1]
                .indexOf("AddedBookISBN="))
                .replace("AddedBookISBN=", "");
        return new String[]{title, author, ISBN};
    }

    public String newCommentRequestParser(Book b, String request) {

        StringBuilder sb = new StringBuilder();
        System.out.println("Comment before: " + b.getComment());
        if (b.getComment().length() == 0) sb.append(request);
        else {
            sb.append(b.getComment())
                    .append("~@~")
                    .append(request);
        }
        System.out.println("Comment after: " + sb.toString());
        return sb.toString();
    }
}

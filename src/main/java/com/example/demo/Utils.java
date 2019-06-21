package com.example.demo;

import java.util.ArrayList;
import java.util.List;

class Utils {
    String[] splitRequestForEditBookPage(String request) {
        String[] titleRequest = request.split("AddedBookAuthor=");
        String title = titleRequest[0].substring(titleRequest[0].indexOf("=") + 1);
        String author = titleRequest[1].substring(0, titleRequest[1].indexOf("AddedBookISBN"));
        String ISBN = titleRequest[1].substring(titleRequest[1]
                .indexOf("AddedBookISBN="))
                .replace("AddedBookISBN=", "");
        return new String[]{title, author, ISBN};
    }

    String newCommentRequestParser(Book b, String request) {
        String newComment = request.substring(request.indexOf("=") + 1);
        List<String> list = new ArrayList<>(b.getComment());
        list.add(newComment);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("~@~");
        }
        String comment = sb.toString().substring(0, sb.length() - 3);
        comment = comment
                .replace("~sem~", ";")
                .replace("~sharp~", "#")
                .replace("~percent~", "%")
                .replace("~question~", "?")
                .replace("~slash~", "/")
                .replace("~backSlash~", "\\")
                .replace("~leftBracket~", "[")
                .replace("~rightBracket~", "]")
                .replace("~wallSlash~", "|");
        return comment;
    }
}

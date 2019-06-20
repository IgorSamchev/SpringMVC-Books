package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void addNewCommentRequestParserTest1stComment() {
        Book b = new Book(1, "Java All-in-One", "Doug Lowe", "978-1119247791", null);
        String request = "756com=Good Book, Java 4-ever";
        String result = Book.newCommentRequestParser(b, request);
        Assert.assertEquals("Good Book, Java 4-ever", result);
    }

    @Test
    public void addNewCommentRequestParserTestNOT1stComment() {
        Book b = new Book(1, "Java All-in-One", "Doug Lowe", "978-1119247791", new String[]{"Good Book, Java 4-ever"});
        String request = "756com=Good Book, Java 4-ever";
        String result = Book.newCommentRequestParser(b, request);
        Assert.assertEquals("Good Book, Java 4-ever~@~Good Book, Java 4-ever", result);
    }

    @Test
    public void addNewCommentRequestParserTestBigCommentWithAllPossibleCharacters() {
        Book b = new Book(1, "Java All-in-One", "Doug Lowe", "978-1119247791", new String[]{"Good Book, Java 4-ever"});
        String request = "756com=!!@@~sharp~~sharp~$$~percent~~percent~^^&&**(())__++~slash~~slash~**--" +
                "~rightBracket~~" + "rightBracket~~leftBracket~~leftBracket~{{}}~sem~~sem~::''\"\",,<<..>>" +
                "~slash~~slash~" + "~question~" + "~question~~wallSlash~~wallSlash~~backSlash~~backSlash~~~";
        String result = Book.newCommentRequestParser(b, request);
        Assert.assertEquals("Good Book, Java 4-ever~@~!!@@##$$%%^^&&**(())__++//**--]][[{{}};;::''\"\",,<<..>>//??||\\\\~~", result);
    }


}
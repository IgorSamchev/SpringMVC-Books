package com.example.demo;

import org.junit.Assert;
import org.junit.Test;


public class BookTest {
    @Test
    public void addNewCommentRequestParserTest1stComment() {
        Book b = new Book(1, "Java All-in-One", "Doug Lowe", "978-1119247791", null);
        String request = "756com=Good Book, Java 4-ever";
        String result = new Utils().newCommentRequestParser(b, request);
        Assert.assertEquals("Good Book, Java 4-ever", result);
    }

    @Test
    public void addNewCommentRequestParserTestNOT1stComment() {
        Book b = new Book(1, "Java All-in-One", "Doug Lowe", "978-1119247791", new String[]{"Good Book, Java 4-ever"});
        String request = "756com=Good Book, Java 4-ever";
        String result = new Utils().newCommentRequestParser(b, request);
        Assert.assertEquals("Good Book, Java 4-ever~@~Good Book, Java 4-ever", result);
    }

    @Test
    public void addNewCommentRequestParserTestBigCommentWithAllPossibleCharacters() {
        Book b = new Book(1, "Java All-in-One", "Doug Lowe", "978-1119247791", new String[]{"Good Book, Java 4-ever"});
        String request = "756com=!!@@~sharp~~sharp~$$~percent~~percent~^^&&**(())__++~slash~~slash~**--" +
                "~rightBracket~~rightBracket~~leftBracket~~leftBracket~{{}}~sem~~sem~::''\"\",,<<..>>" +
                "~slash~~slash~~question~~question~~wallSlash~~wallSlash~~backSlash~~backSlash~~~";
        String result = new Utils().newCommentRequestParser(b, request);
        Assert.assertEquals("Good Book, Java 4-ever~@~!!@@##$$%%^^&&**(())__++//**--]][[{{}};;::''\"\",,<<..>>//??||\\\\~~", result);
    }

    @Test
    public void editBookRequestDataSplitTest() {
        String request = "812" +
                "AddedBookTitle=BookTitle" +
                "AddedBookAuthor=BookAuthor" +
                "AddedBookISBN=BookISBN";
        String[] result = new Utils().splitRequestForEditBookPage(request);
        Assert.assertArrayEquals(new String[]{"BookTitle", "BookAuthor", "BookISBN"}, result);
     }
}
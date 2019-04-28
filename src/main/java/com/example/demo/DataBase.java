package com.example.demo;

import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

class DataBase {
    private static String url = "jdbc:postgresql://ec2-54-221-236-144.compute-1.amazonaws.com:5432/d11pd7jijnokmj";
    private static String user = "jwzorstluduoav";
    private static String password = "e6208131a87f8c2383632ad3b69c5b6cede00fb46b802676b323e97a650ac83a";


    static void addNewBook(String title, String author, String isbn) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            PreparedStatement st = conn.prepareStatement("INSERT INTO books (title, author, isbn, comment) VALUES (?, ?, ?, ?)");

            st.setString(1, title);
            st.setString(2, author);
            st.setString(3, isbn);
            st.setArray(4, null);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateBook(String id, String[] data) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement st = conn.createStatement();
            String query = "UPDATE books " +
                    "SET " +
                    "title = '" + data[0] +
                    "', author = '" + data[1] +
                    "', isbn = '" + data[2] +
                    "' WHERE id = " + id;
            st.executeQuery(query);


        } catch (SQLException ignored) {
        }

    }

    static void addComment(int id, String commentArray) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement st = conn.createStatement();
            String query = "UPDATE books " +
                    "SET " +
                    "comment = '{" + commentArray + "}' WHERE id = " + id;
            st.executeQuery(query);

        } catch (SQLException ignored) {
        }
    }

    List<Book> getBooksList() {
        List<Book> booksList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);

             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                String[] comment = null;
                if (rs.getString(5) != null) {

                    comment = rs.getString(5).split("~@~");
                    comment[0] = comment[0].substring(1);
                    comment[comment.length - 1] = comment[comment.length - 1].substring(0, comment[comment.length - 1].length() - 1);
                }
                booksList.add(new Book(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        comment));
            }
            booksList.sort(Comparator.comparingInt(Book::getID));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return booksList;
    }

    static void deleteBookById(long id) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sqlQuery = "DELETE FROM \"public\".\"books\" WHERE \"id\" = " + id;
            Statement st = con.createStatement();
            st.executeQuery(sqlQuery);

        } catch (SQLException ignored) {
        }
    }
}
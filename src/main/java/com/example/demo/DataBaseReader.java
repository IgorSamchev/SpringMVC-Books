package com.example.demo;

import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

class DataBaseReader {

    List<Book> getBooksList() {
        List<Book> booksList = new ArrayList<>();
        String url = "jdbc:postgresql://ec2-54-221-236-144.compute-1.amazonaws.com:5432/d11pd7jijnokmj";
        String user = "jwzorstluduoav";
        String password = "e6208131a87f8c2383632ad3b69c5b6cede00fb46b802676b323e97a650ac83a";

        try (Connection con = DriverManager.getConnection(url, user, password);

             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                booksList.add(new Book(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5) == null ? "---" : rs.getString(5)));
            }

            booksList.sort(Comparator.comparing((Book b) -> String.valueOf(b.getID())));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return booksList;
    }
}

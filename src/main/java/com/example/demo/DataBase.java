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

    static void log(String timeStamp, String logText){
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            PreparedStatement st = conn.prepareStatement("INSERT INTO logs (day, time, message) VALUES (?,?,?)");
            String[] date = timeStamp.split(" ");
            st.setString(1, date[0]);
            st.setString(2, date[1]);
            st.setString(3, logText);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            st.close();

        } catch (SQLException ignored) {
        }
    }

    static void addComment(int id, String commentArray) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE books "
                    + "SET comment = ? "
                    + "WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, commentArray);
            preparedStatement.setInt(2 , id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException ignore) {
        }
    }

    static void reset() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("React Quickly", "Azat Mardan", "978-1617293344", null));
        list.add(new Book("Effective Java 3rd Edition", "Joshua Bloch", "978-0134685991", null));
        list.add(new Book("Head First Design Patterns", "Eric Freeman", "978-0596007126", null));
        list.add(new Book("Elements of Programming", "Adnan Aziz", "978-1517671273", null));
        list.add(new Book("Storage and Analysis", "Tom White", "978-1491901632", null));
        list.add(new Book("Oracle Certified Associate", "Jeanne Boyarsky", "978-1118957400", null));
        list.add(new Book("Core Java Volume I", "Cay S. Horstmann", "978-0135166307", null));
        list.add(new Book("Modern Java in Action", "Azat Mardan", "978-1617293346", null));
        list.add(new Book("React Quickly", "Raoul-Gabriel Urma", "978-1617293340", null));
        list.add(new Book("Java All-in-One", "Doug Lowe", "978-1119247791", null));
        list.add(new Book("Introduction to Java", "Y. Daniel Liang", "978-0134670942", null));
        list.add(new Book("Python for Programmers", "Paul J. Deitel", "978-0135224335", null));
        list.add(new Book("Mastering Java", "Michael B. White", "978-1792070112", null));
        list.add(new Book("Java in 24 Hours", "Rogers Cadenhead", " 978-0672337949", null));
        list.add(new Book("Regular Expression", "Tony Stubblebine", "978-0596514273", null));
        list.add(new Book("Robot Programming", "Cameron Hughes", "978-0789755001", null));
        list.add(new Book("Cloud Native Patterns", "Cornelia Davis ", "978-1617294297", null));


        for (Book b : list) {
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO books (title, author, isbn, comment) VALUES (?, ?, ?, ?)");

                st.setString(1, b.getTitle());
                st.setString(2, b.getAuthor());
                st.setString(3, b.getISBN());
                st.setArray(4, null);
                st.executeUpdate();
                st.close();
            } catch (SQLException ignored) {
            }

        }
    }

    static List<Logger> getLogs() {
        List<Logger> logsList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);

             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM logs ORDER BY id DESC")) {

            while (rs.next()) {
                String date = rs.getString(2);
                String time = rs.getString(3);
                String message = rs.getString(4);

                logsList.add(new Logger(date, time, message));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return logsList;
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

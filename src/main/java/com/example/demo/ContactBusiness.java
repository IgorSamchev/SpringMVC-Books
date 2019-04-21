package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ContactBusiness {

    public List<Contact> getContactList(){
        List<Contact> listContact = new ArrayList<>();
        String url = "jdbc:postgresql://ec2-54-221-236-144.compute-1.amazonaws.com:5432/d11pd7jijnokmj";
        String user = "jwzorstluduoav";
        String password = "e6208131a87f8c2383632ad3b69c5b6cede00fb46b802676b323e97a650ac83a";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Books")) {

            while (rs.next()){
                listContact.add(new Contact(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
                System.out.println(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }




//        listContact.add(new Contact (1, "Jane Austen", "Pride and Prejudice", "332-12323131"));
//        listContact.add(new Contact (2, "Harper Lee", "To Kill a Mockingbird", "125-12323251"));
//        listContact.add(new Contact (3, "Dan Brown", "The Da Vinci Code", "142-12323213"));
//        listContact.add(new Contact (4, "J. D. Salinger", "The Catcher in the Rye", "213-12323213"));

        return listContact;
    }
}

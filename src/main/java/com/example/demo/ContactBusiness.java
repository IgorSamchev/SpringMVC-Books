package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ContactBusiness {

    public List<Contact> getContactList(){
        List<Contact> listContact = new ArrayList<>();

        listContact.add(new Contact (1, "Jane Austen", "Pride and Prejudice", "332-12323131"));
        listContact.add(new Contact (2, "Harper Lee", "To Kill a Mockingbird", "125-12323251"));
        listContact.add(new Contact (3, "Dan Brown", "The Da Vinci Code", "142-12323213"));
        listContact.add(new Contact (4, "J. D. Salinger", "The Catcher in the Rye", "213-12323213"));

        return listContact;
    }
}

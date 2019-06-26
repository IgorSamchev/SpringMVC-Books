package com.example.demo.dao;

import com.example.demo.models.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.demo.Utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;

public class BookDao {

    public Book findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Book.class, id);
    }

    public void save(Book user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(Book user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(Book user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Book findAutoById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Book.class, id);
    }


    public List<Book> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Book> books = (List<Book>) session.createQuery("from Book ").list();
        session.close();
        books.sort(Comparator.comparingInt(Book::getId));
        return books;
    }
}

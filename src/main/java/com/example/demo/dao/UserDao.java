package com.example.demo.dao;

import com.example.demo.Utils.HibernateSessionFactoryUtil;
import com.example.demo.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Comparator;
import java.util.List;

public class UserDao {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<User> users = (List<User>) session.createQuery("from User ").list();
        session.close();
        users.sort(Comparator.comparingInt(User::getId));
        return users;
    }
}

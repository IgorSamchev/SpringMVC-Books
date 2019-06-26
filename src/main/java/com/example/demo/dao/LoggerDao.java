package com.example.demo.dao;

import com.example.demo.Utils.HibernateSessionFactoryUtil;
import com.example.demo.models.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Comparator;
import java.util.List;

public class LoggerDao {
    public void save(Logger log) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(log);
        tx1.commit();
        session.close();
    }

    public List<Logger> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Logger> logs = (List<Logger>) session.createQuery("from Logger ").list();
        session.close();
        logs.sort(Comparator.comparingInt(Logger::getId).reversed());
        return logs;
    }
}

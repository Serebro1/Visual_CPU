package com.example.visualcpu.visualAndDAO;

import com.example.visualcpu.cpu.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class testHibernateDB {
    public static void main(String[] args) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Command p1 = new Command(Task.ld, 1, 2);
        session.save(p1);
        Command p2 = new Command(Task.ld, 2, 2);
        session.save(p2);
        tx1.commit();
        session.close();

        List<Command> marks = (List<Command>)HibernateSessionFactoryUtil.
                getSessionFactory().openSession().createQuery("From Command").list();

        marks.forEach(System.out::println);
    }
}

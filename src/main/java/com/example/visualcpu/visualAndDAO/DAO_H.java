package com.example.visualcpu.visualAndDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DAO_H extends DAO_Commands{

    public DAO_H(){
        updateList();
    }

    public void addCom(Command c){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(c);
        tx1.commit();
        session.close();

        updateList();
    }

    public void removeCom(Command c) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(c);
        tx1.commit();
        session.close();

        updateList();
    }
    public void swapComs(Command c1, Command c2){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        int tmp = c1.ID;
        c1.ID = c2.ID;
        c2.ID = tmp;
        session.merge(c1);
        session.merge(c2);
        tx1.commit();
        session.close();

        updateList();
    }

    protected void updateList(){
        comms.clear();
        List<Command> commands = (List<Command>)HibernateSessionFactoryUtil.
                getSessionFactory().openSession().createQuery("From Command").list();
        comms.addAll(commands);
    }


}

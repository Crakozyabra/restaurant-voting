package com.restaurant.voting.developtools;

import com.restaurant.voting.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateSessionFactory {
    private static HibernateSessionFactory instance;
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
        Properties properties = new Properties();


        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        properties.put(Environment.USER, "user");
        properties.put(Environment.PASS, "password");
       properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);

        sessionFactory = new Configuration()
        .addProperties(properties)
        .addAnnotatedClass(Menu.class)
                .addAnnotatedClass(Restaurant.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Vote.class)
                .addAnnotatedClass(Menu.class)
                .addAnnotatedClass(ProductSupplier.class)
                .buildSessionFactory();
    }

    public static SessionFactory getHibernateSessionFactory(){
        if (sessionFactory == null) {
            instance = new HibernateSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
       try(Session session = HibernateSessionFactory.getHibernateSessionFactory().openSession()) {
           Transaction transaction = session.getTransaction();
           transaction.begin();

           transaction.commit();
       }
    }

}

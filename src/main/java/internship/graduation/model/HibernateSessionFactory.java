package internship.graduation.model;

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
        //properties.put(Environment.DRIVER, "org.hsqldb.jdbcDriver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        //properties.put(Environment.URL, "jdbc:hsqldb:mem:dbstorage");
        //properties.put(Environment.DIALECT, "org.hibernate.dialect.HSQLDialect");
        properties.put(Environment.USER, "user");
       // properties.put(Environment.USER, "sa");
       // properties.put(Environment.PASS, "");
        properties.put(Environment.PASS, "password");
       properties.put(Environment.HBM2DDL_AUTO, "create");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);

        sessionFactory = new Configuration()
        .addProperties(properties)
        .addAnnotatedClass(Menu.class)
                .addAnnotatedClass(Restaurant.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Vote.class).addAnnotatedClass(Menu.class)
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
          /* Restaurant restaurant = new Restaurant(null, "some restaurant",null,null);
           session.persist(restaurant);*/
           /*MenuItem menuItem1 = new MenuItem("item 1", 300.3, true);
           MenuItem menuItem2 = new MenuItem("item 2", 30000.3, true);
           restaurant.setMenuItems(List.of(menuItem1, menuItem2));*/
          /* Restaurant restaurant = session.find(Restaurant.class, 5);
           Vote vote = new Vote();
           User user = new User();
           user.setName("username");
           user.setEmail("user@ru");
           session.persist(user);
           vote.setUser(user);
           vote.setRestaurant(restaurant);
           session.persist(vote);*/
           transaction.commit();
       }
    }

}

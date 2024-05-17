package org.example.util;

import org.example.Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getsessionFactory(){

        if(sessionFactory == null){
           try {
               Configuration configuration = new Configuration();

               // Hibernate settings equivalent to hibernate.cfg.xml's properties
               Properties settings = new Properties();
               settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
               settings.put(Environment.URL, "jdbc:mysql://localhost:3306/ashik");
               settings.put(Environment.USER, "root");
               settings.put(Environment.PASS, "Ash!k@23402");

               settings.put(Environment.SHOW_SQL, "true");

               settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

               settings.put(Environment.HBM2DDL_AUTO, "create-drop");

               configuration.setProperties(settings);
               configuration.addAnnotatedClass(BaseEntity.class);
               configuration.addAnnotatedClass(User.class);
               configuration.addAnnotatedClass(Team.class);
               configuration.addAnnotatedClass(Role.class);
               configuration.addAnnotatedClass(Leave.class);
               configuration.addAnnotatedClass(Attendence.class);

               StandardServiceRegistry build = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
               sessionFactory = configuration.buildSessionFactory(build);
           }catch (Exception e){
               e.printStackTrace();
           }
        }

        return sessionFactory;


    }
}

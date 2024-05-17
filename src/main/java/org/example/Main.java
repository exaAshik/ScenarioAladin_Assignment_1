package org.example;

import org.example.util.HibernateUtils;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        Session session = HibernateUtils.getsessionFactory().openSession();
    }
}
package org.example;

import org.example.Entities.Role;
import org.example.Entities.Team;
import org.example.Entities.User;
import org.example.util.HibernateUtils;
import org.hibernate.Session;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        Session session = HibernateUtils.getsessionFactory().openSession();
    }
}
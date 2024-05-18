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

        User user = new User();

        // Set user properties
        user.setEmployeeId("EMP001");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setPosition("Software Engineer");
        user.setEmploymentStatus("Full-time");
        user.setGender("Male");
        user.setBloodGroup("O+");
        user.setBirthDate(LocalDate.of(1990, 5, 15));
        user.setNidNumber("1234567890");
        user.setTinNumber("0987654321");
        user.setContactNumber("+1234567890");

        // Create a new Team object and set it for the user
        Team team = new Team();
        team.setTeamName("Development Team");
        user.setTeam(team);

        // Create a new Role object and set it for the user
        Role role = new Role();
        role.setRoleName("Developer");
        user.getRoles().add(role);

        // Save the user using Hibernate

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

        System.out.println(user.getId()+" "+user.getRoles()+" "+user.getTeam());
    }
}
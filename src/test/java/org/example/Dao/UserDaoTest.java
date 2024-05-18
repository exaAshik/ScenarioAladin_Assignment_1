package org.example.Dao;

import junit.framework.TestCase;
import org.example.Entities.Role;
import org.example.Entities.Team;
import org.example.Entities.User;
import org.example.util.HibernateUtils;
import org.example.util.PasswordHashing;
import org.hibernate.SessionFactory;

import java.util.UUID;

public class UserDaoTest extends TestCase {

    public SessionFactory sessionFactory;

    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory=HibernateUtils.getsessionFactory();
    }



    public void testCreateUser() {
        User user = new User();
        user.setEmployeeId(UUID.randomUUID().toString());
        user.setName("Shams");
        user.setEmail("shamas@exabyting.com");
        user.setPassword(PasswordHashing.hashPassword("shams"));
        user.setPosition("Enginner manager");
        user.setEmploymentStatus("Permanent");
        user.setGender("Male");
        Team team = new Team();
        team.setTeamName("GGWP");
        UserDao userDao  = new UserDao(sessionFactory);
        User user1 = userDao.createUser(user);
        System.out.println(user1 +"id "+ user1.getId() + " "+ user1.getTeam()+" "+ user1.getRoles());
        assertNotNull(user1);
    }

    public void testUpdateUser(){
        User user = new User();
        user.setId(2);
        user.setEmployeeId(UUID.randomUUID().toString());
        UserDao userDao  = new UserDao(sessionFactory);
        User user1 = userDao.updateUser(user);
        assertNotNull(user1);

    }


    public void testGetUserById(){
        testCreateUser();
        testUpdateUser();
        UserDao userDao = new UserDao(sessionFactory);
        User userById = userDao.getUserById(1);
        assertNotNull(userById);
    }

    public void testDeleteUserById(){
        testCreateUser();
        testUpdateUser();
        UserDao userDao = new UserDao(sessionFactory);
        boolean b = userDao.deleteUser(1);
        assertTrue(true);
    }

    public void testUserByEmail(){
        testCreateUser();
        UserDao userDao = new UserDao(sessionFactory);
        User byEmail = userDao.findByEmail("ashik@exabyting.com");
        assertNotNull(byEmail);
    }



}
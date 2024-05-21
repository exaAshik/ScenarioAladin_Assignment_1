package org.example.Dao;

import junit.framework.TestCase;
import org.example.AppConstant;
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
        user.setName("ashik");
        user.setEmail("ashik@exabyting.com");
        user.setPassword(PasswordHashing.hashPassword("ashik"));
        user.setPosition("Software Engineer Trainee");
        user.setEmploymentStatus("Permanent");
        user.setGender("Male");

        TeamDao team = new TeamDao(sessionFactory);
        Team teamById = team.getTeamById(1);

        RoleDao roleDao = new RoleDao(sessionFactory);
        Role roleById = roleDao.getRoleById(AppConstant.roleId);

        Role role = new Role();
        role.setRoleName("Normal");
        user.setTeam(teamById);
        user.getRoles().add(roleById);
        UserDao userDao  = new UserDao(sessionFactory);
        User user1 = userDao.createUser(user);
        System.out.println(user1 +"id "+ user1.getId() + " "+ user1.getTeam()+" "+ user1.getRoles());
        assertNotNull(user1);
    }

    public void testUpdateUser(){
        UserDao userDao  = new UserDao(sessionFactory);
        User user = new User();
        User userById = userDao.getUserById(AppConstant.userId);
        Role role = new Role();
        role.setRoleName(AppConstant.adminRole);
        userById.getRoles().add(role);
        User user1 = userDao.updateUser(userById);
        assertNotNull(user1);

    }


    public void testGetUserById(){
        UserDao userDao = new UserDao(sessionFactory);
        User userById = userDao.getUserById(AppConstant.userId);
        assertNotNull(userById);
    }

    public void testDeleteUserById(){

        UserDao userDao = new UserDao(sessionFactory);
        boolean b = userDao.deleteUser(AppConstant.userId);
        assertTrue(true);
    }

    public void testUserByEmail(){
        testCreateUser();
        UserDao userDao = new UserDao(sessionFactory);
        User byEmail = userDao.findByEmail(AppConstant.userEmail);
        assertNotNull(byEmail);
    }



}
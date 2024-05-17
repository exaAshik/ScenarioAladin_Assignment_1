package org.example.Dao;

import junit.framework.TestCase;
import org.example.Entities.User;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

public class UserDaoTest extends TestCase {

    public SessionFactory sessionFactory;

    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory=HibernateUtils.getsessionFactory();
    }



    public void testCreateUser() {
        User user = new User();
        user.setName("ashik");
        user.setEmail("ashik@exabyting.com");
        UserDao userDao  = new UserDao(sessionFactory);
        User user1 = userDao.createUser(user);
        assertNotNull(user1);
    }
}
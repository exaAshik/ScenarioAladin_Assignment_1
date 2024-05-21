package org.example.Services.Implementation;

import junit.framework.TestCase;
import org.example.Dao.UserDao;
import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.example.Entities.User;
import org.example.Services.NormalServices;
import org.example.util.HibernateUtils;
import org.example.util.LoginRequest;
import org.hibernate.SessionFactory;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.List;

public class ImplementationNormalTest extends TestCase {

    public SessionFactory sessionFactory;

    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory= HibernateUtils.getsessionFactory();
    }


    public void testLogIn() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("riyadh@exabyting.com");
        loginRequest.setUserPassword("riyadh");
        NormalServices normalServices =new ImplementationNormal(sessionFactory);
        User user = normalServices.logIn(loginRequest);
        Assert.assertNotNull(user);
        System.out.println("Welcome "+ user.getName());
    }

    public void testClockIn() {

        NormalServices normalServices =new ImplementationNormal(sessionFactory);
        Attendence attendence = normalServices.clockIn(2);
        assertNotNull(attendence);

    }

    public void testClockOut() {

        NormalServices normalServices =new ImplementationNormal(sessionFactory);
        Attendence attendence = normalServices.clockOut(2);
        assertNotNull(attendence);

    }

    public void testApplyLeave() {

        NormalServices normalServices =new ImplementationNormal(sessionFactory);
//        UserDao userDao = new UserDao(sessionFactory);
//        User userById = userDao.getUserById(52);
        Leave leave = new Leave();
        leave.setLeaveStartDate(LocalDate.now().plusDays(6));
        leave.setLeaveEndDate(LocalDate.now().plusDays(8));
        leave.setLeaveReason("urgent");
        leave.setLeaveType("casual");
        Leave leave1 = normalServices.applyLeave(leave, 102);

        assertNotNull(leave1);

    }

    public void testMyLeave() {
        NormalServices normalServices =new ImplementationNormal(sessionFactory);
        List<Leave> leaves = normalServices.myLeave(1);
        System.out.println(leaves);
        assertNotNull(leaves.get(0));
    }

    public void testMyTeamLeaves() {
        NormalServices normalServices =new ImplementationNormal(sessionFactory);
        List<Leave> leaves = normalServices.myTeamLeaves(1);
        System.out.println(leaves);
        assertNotNull(leaves.get(0));
    }
}
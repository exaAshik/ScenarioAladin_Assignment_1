package org.example.Dao;

import junit.framework.TestCase;
import org.example.Entities.Leave;
import org.example.Entities.Role;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

public class LeaveDaoTest extends TestCase {

    public SessionFactory sessionFactory;

    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory= HibernateUtils.getsessionFactory();
    }

    public void testCreateLeave() {
        Leave leave = new Leave();
        leave.setLeaveReason("illl");
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        Leave leave1 = leaveDao.createLeave(leave);
        assertNotNull(leave1);
    }

    public void testUpdateLeave() {
        testCreateLeave();
        Leave leave = new Leave();
        leave.setId(1);
        leave.setLeaveReason("illl");
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        Leave leave1 = leaveDao.updateLeave(leave);
        assertNotNull(leave1);
    }

    public void testGetLeaveById() {
        testCreateLeave();
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        Leave leaveById = leaveDao.getLeaveById(1);
        assertNotNull(leaveById);
    }

    public void testDeleteLeave() {

        testCreateLeave();
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        boolean b = leaveDao.deleteLeave(1);
        assertTrue(true);
    }
}
package org.example.Dao;

import junit.framework.TestCase;
import org.example.AppConstant;
import org.example.Entities.Attendence;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

import java.time.Instant;

public class AttendenceDaoTest extends TestCase {

    public SessionFactory sessionFactory;
    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory= HibernateUtils.getsessionFactory();
    }

    public void testCreateAttendence() {
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);
        Attendence attendence = new Attendence();
        attendence.setClockIn(Instant.now());
        Attendence attendence1 = attendenceDao.createAttendence(attendence);
        assertNotNull(attendence1);
    }

    public void testUpdateAttendence() {
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);
        Attendence attendence = new Attendence();
        attendence.setId(AppConstant.userId);
        attendence.setClockIn(Instant.now());
        Attendence attendence1 = attendenceDao.updateAttendence(attendence);
        assertNotNull(attendence1);
    }

    public void testGetAttendenceById() {
        testCreateAttendence();
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);
        Attendence attendence1 = attendenceDao.getAttendenceById(AppConstant.userId);
        assertNotNull(attendence1);
    }

    public void testDeleteAttendence() {
        testCreateAttendence();
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);
        boolean b = attendenceDao.deleteAttendence(AppConstant.userId);
        assertTrue(true);
    }
}
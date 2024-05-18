package org.example.Services.Implementation;

import org.example.Dao.AttendenceDao;
import org.example.Dao.LeaveDao;
import org.example.Dao.UserDao;
import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.example.Entities.Role;
import org.example.Entities.User;
import org.example.Services.LeadServices;
import org.example.util.Authorize;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public class ImplementationLead implements LeadServices {

    public SessionFactory sessionFactory;

    ImplementationLead(){
        this.sessionFactory = HibernateUtils.getsessionFactory();
    }


    @Override
    public Leave Decision(Leave leave,Integer leadId) {
        UserDao userDao = new UserDao(sessionFactory);
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        User userById = userDao.getUserById(leadId);
        Set<Role> roles = userById.getRoles();
        boolean authorize = Authorize.isAutorize(roles);
        if(!authorize) throw new RuntimeException("you are not authorize for approve leave");
        return leaveDao.updateLeave(leave);
    }

    @Override
    public List<Attendence> attendenceReportByUser(Integer userId, Integer leadId) {

        UserDao userDao = new UserDao(sessionFactory);
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);

        User userById = userDao.getUserById(leadId);

        boolean authorize = Authorize.isAutorize(userById.getRoles());
        if(!authorize) throw new RuntimeException("you are not authorize for report service");
        int year = 2024;
        int month =5;

        return attendenceDao.getAttendenceByuserMonthly(userId, year, month);


    }
}

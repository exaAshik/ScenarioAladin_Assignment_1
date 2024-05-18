package org.example.Services.Implementation;

import org.example.Dao.LeaveDao;
import org.example.Dao.UserDao;
import org.example.Entities.Leave;
import org.example.Services.Admin;
import org.example.util.Authorize;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class ImplementationAdmin implements Admin {


    public SessionFactory sessionFactory;

    ImplementationAdmin(){
        this.sessionFactory=HibernateUtils.getsessionFactory();
    }

    @Override
    public List<Leave> getAllLeave(Integer adminId) {
        return null;
    }

    @Override
    public Leave modifyLeave(Integer AdminId,Leave leave) {
        UserDao userDao = new UserDao(sessionFactory);
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        boolean authorize = Authorize.isAutorize(userDao.getUserById(AdminId).getRoles());
        if(!authorize) throw new RuntimeException("you are not authorized");
        return leaveDao.updateLeave(leave);
    }
}

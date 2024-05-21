package org.example.Services.Implementation;

import org.example.Dao.AttendenceDao;
import org.example.Dao.LeaveDao;
import org.example.Dao.UserDao;
import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.example.Entities.User;
import org.example.Services.NormalServices;
import org.example.util.HibernateUtils;
import org.example.util.LoginRequest;
import org.example.util.PasswordHashing;
import org.hibernate.SessionFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class ImplementationNormal implements NormalServices {

    public SessionFactory sessionFactory;

    ImplementationNormal(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public User logIn(LoginRequest loginRequest) {
        UserDao userDao = new UserDao(sessionFactory);
        User byEmail = userDao.findByEmail(loginRequest.getUserEmail());
        String password = byEmail.getPassword();
        if(password.equals(PasswordHashing.hashPassword(loginRequest.getUserPassword()))){
            return byEmail;
        }else {
            throw new RuntimeException("Credental not matched");
        }
    }

    @Override
    public Attendence clockIn(Integer userId) {

        UserDao userDao = new UserDao(sessionFactory);
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);
        User userById = userDao.getUserById(userId);
        if(userById != null){
            Attendence attendence = attendenceDao.getAttendenceByUserIdAndDate(userId, LocalDate.now());
            if(attendence != null){
                throw  new RuntimeException("you already complete your today attendence");
            }
            Attendence attendence1 = new Attendence();
            attendence1.setClockIn(Instant.now());
            attendence1.setUser(userById);
            attendence1.setDate(LocalDate.now());
            return attendenceDao.createAttendence(attendence1);

        }
        return null;
    }

    @Override
    public Attendence clockOut(Integer userId) {

        UserDao userDao = new UserDao(sessionFactory);
        AttendenceDao attendenceDao = new AttendenceDao(sessionFactory);
        User userById = userDao.getUserById(userId);

        if(userById != null){
            Attendence attendence = attendenceDao.getAttendenceByUserIdAndDate(userId, LocalDate.now());
            if(attendence == null) throw  new RuntimeException("you didnt clockIn today");
            attendence.setClockOut(Instant.now());
            return attendenceDao.updateAttendence(attendence);
        }

        return null;
    }

    @Override
    public Leave applyLeave(Leave requestLeave, Integer userId) {

        UserDao userDao = new UserDao(sessionFactory);
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        User userById = userDao.getUserById(userId);
        requestLeave.setLeaveStatus("Pending");
        requestLeave.setUser(userById);
        Leave leave = leaveDao.createLeave(requestLeave);
        return leave;
    }

    @Override
    public List<Leave> myLeave(Integer userId) {

        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        List<Leave> leavesByUserId = leaveDao.getLeavesByUserId(userId);
        return leavesByUserId;
    }

    @Override
    public List<Leave> myTeamLeaves(Integer userId) {
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        return  leaveDao.getleavesofUserTeam(userId);

    }
}

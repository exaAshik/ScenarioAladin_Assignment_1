package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.example.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class AttendenceDao {

    public SessionFactory sessionFactory;

    public Attendence createAttendence(Attendence attendence){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(attendence);
            transaction.commit();
            session.close();
            return attendence;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Attendence updateAttendence(Attendence attendence){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.merge(attendence);
            transaction.commit();
            session.close();
            return attendence;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Attendence getAttendenceById(Integer id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Attendence attendence = session.get(Attendence.class, id);
            transaction.commit();
            session.close();
            return attendence;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public Attendence getAttendenceByUserIdAndDate(Integer userId, LocalDate date){

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Query<Attendence> query = session.createQuery("FROM Attendence WHERE user.id = :userId AND date = :date", Attendence.class);
            query.setParameter("userId",userId);
            query.setParameter("date",date);
            return query.uniqueResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Attendence>getAttendenceByuserMonthly(Integer userId,int year, int month){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        String hql = "SELECT a FROM Attendence a " +
                "JOIN a.user u " +
                "WHERE u.id = :userId " +
                "AND YEAR(a.date) = :year " +
                "AND MONTH(a.date) = :month";

        Query<Attendence> query = session.createQuery(hql, Attendence.class);
        query.setParameter("userId", userId);
        query.setParameter("year", year);
        query.setParameter("month",month);
        return query.getResultList();

    }

    public boolean deleteAttendence(Integer attendenceId){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Attendence attendence = session.get(Attendence.class, attendenceId);
            session.remove(attendence);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }



}

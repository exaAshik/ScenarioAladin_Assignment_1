package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

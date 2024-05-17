package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Entities.Leave;
import org.example.Entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


@Data
@AllArgsConstructor
public class LeaveDao {

    public SessionFactory sessionFactory;


    public Leave createLeave(Leave leave){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(leave);
            transaction.commit();
            session.close();
            return leave;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Leave updateLeave(Leave leave){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.merge(leave);
            transaction.commit();
            session.close();
            return leave;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Leave getLeaveById(Integer id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Leave leave = session.get(Leave.class, id);
            transaction.commit();
            session.close();
            return leave;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean deleteLeave(Integer leaveId){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Leave leave= session.get(Leave.class, leaveId);
            session.remove(leave);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }




}

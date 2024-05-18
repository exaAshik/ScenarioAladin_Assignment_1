package org.example.Dao;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Entities.Leave;
import org.example.Entities.Role;
import org.example.Entities.Team;
import org.example.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;


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

    public List<Leave>getLeavesByUserId(Integer userId){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query<Leave> query = session.createQuery("FROM user_leave WHERE user_id = :userId", Leave.class);
        query.setParameter("user_id",userId);
        List<Leave> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;

    }

    public List<Leave>getleavesofUserTeam(Integer userId){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        User user = session.get(User.class, userId);
        Integer teamId = user.getTeam().getId();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Leave> query = criteriaBuilder.createQuery(Leave.class);

        Root<Leave> leaveRoot = query.from(Leave.class);

        Join<Leave, User> userJoin = leaveRoot.join("user", JoinType.INNER);
        Join<User, Team> teamJoin = userJoin.join("team", JoinType.INNER);

        query.select(leaveRoot)
                .where(criteriaBuilder.notEqual(teamJoin.get("id"), teamId));
        List<Leave> resultList = session.createQuery(query).getResultList();

        return resultList;


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

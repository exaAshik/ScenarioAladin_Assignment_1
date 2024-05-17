package org.example.Dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Entities.Team;
import org.example.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Data
@AllArgsConstructor
public class TeamDao {

    public SessionFactory sessionFactory;

    public Team createTeam(Team team){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(team);
            transaction.commit();
            session.close();
            return team;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Team updateTeam(Team team){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.merge(team);
            transaction.commit();
            session.close();
            return team;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Team getTeamById(Integer id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Team team = session.get(Team.class, id);
            transaction.commit();
            session.close();
            return team;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean deleteTeam(Integer teamId){

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Team team = session.get(Team.class, teamId);
            session.remove(team);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }



}

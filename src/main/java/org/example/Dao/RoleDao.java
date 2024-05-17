package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Entities.Role;
import org.example.Entities.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


@Data
@AllArgsConstructor
public class RoleDao {

    public SessionFactory sessionFactory;

    public Role createRole(Role role){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(role);
            transaction.commit();
            session.close();
            return role;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Role updateRole(Role role){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.merge(role);
            transaction.commit();
            session.close();
            return role;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Role getRoleById(Integer id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Role role = session.get(Role.class, id);
            transaction.commit();
            session.close();
            return role;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean deleteRole(Integer roleId){

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Role role = session.get(Role.class, roleId);
            session.remove(role);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }




}

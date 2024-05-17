package org.example.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Data
@AllArgsConstructor
public class UserDao {

    public SessionFactory sessionFactory;



    public User createUser(User user){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(user);
            transaction.commit();
            session.close();
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

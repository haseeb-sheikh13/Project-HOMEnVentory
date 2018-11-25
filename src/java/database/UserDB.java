package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Category;
import model.Item;
import model.User;
/**
 *
 * @author 687159
 */
public class UserDB 
{
    public static int insert(User user)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.persist(user);
            trans.commit();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e);
            trans.rollback();
        }
        finally
        {
            em.close();
        }
        return 0;
    }
    
    public static int update(User user)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.merge(user);
            trans.commit();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e);
            trans.rollback();
        }
        finally
        {
            em.close();
        }
        return 0;
    }
    
    public List<User> getAll()throws HomeInventoryDBException 
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;                
        } 
        finally 
        {
            em.close();
        }
    }
    
    public User getUser(String username)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            User user = em.find(User.class, username);
            return user;
        } 
        finally 
        {
            em.close();
        }
    }
    
    public static int delete(User user)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.remove(em.merge(user));
            trans.commit();
            return 1;
        }
        catch(Exception e) 
        {
            System.out.println(e);
            trans.rollback();
        }
        finally
        {
            em.close();
        }
        return 0;
    }
}

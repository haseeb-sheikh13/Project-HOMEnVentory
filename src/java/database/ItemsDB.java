package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Item;

/**
 *
 * @author 687159
 */
public class ItemsDB 
{
    public static int insert(Item item)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.persist(item);
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
    
    public static int update(Item item)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.merge(item);
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
    
    public List<Item> getAll()throws HomeInventoryDBException 
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
            return items;                
        } 
        finally 
        {
            em.close();
        }
    }
    
    public Item getItem(int itemID)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            Item item = em.find(Item.class, itemID);
            return item;
        } 
        finally 
        {
            em.close();
        }
    }
    
    public Item getItemName(String itemName)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            Item item = em.find(Item.class, itemName);
            return item;
        } 
        finally 
        {
            em.close();
        }
    }
    
    public static int delete(Item item)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.remove(em.merge(item));
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

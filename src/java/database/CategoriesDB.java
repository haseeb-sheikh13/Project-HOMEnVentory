package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Category;

/**
 *
 * @author 687159
 */
public class CategoriesDB 
{
    public static int insert(Category category)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.persist(category);
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
    
    public static int update(Category category)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.merge(category);
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
    
    public List<Category> getAll()throws HomeInventoryDBException 
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            List<Category> category = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return category;                
        } 
        finally 
        {
            em.close();
        }
    }
    
    public Category getCategory(int categoryID)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try 
        {
            Category category = em.find(Category.class, categoryID);
            return category;
        } 
        finally 
        {
            em.close();
        }
    }
    
    public static int delete(Category category)throws HomeInventoryDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.remove(em.merge(category));
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

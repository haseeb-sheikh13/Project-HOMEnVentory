package services;

import database.CategoriesDB;
import java.util.List;
import model.Category;
import model.Item;
import model.User;

/**
 *
 * @author 687159
 */
public class CategoryService 
{
    private CategoriesDB catDB = new CategoriesDB();
    private Category category = new Category();
    public CategoryService() 
    {
        catDB = new CategoriesDB();
    }
    
    public Category getCategory(int categoryID) throws Exception
    {
        return catDB.getCategory(categoryID);
    }
    
    public List<Category> getAllCategories() throws Exception 
    {
        return catDB.getAll();
    }
    
    public int update(int categoryID, String categoryName) throws Exception
    {
        category.setCategoryID(categoryID);
        category.setCategoryName(categoryName);
        return catDB.update(category);
    }
    
    public int insertCategory(String categoryName) throws Exception {
        category = new Category();
        category.setCategoryName(categoryName);
        return catDB.insert(category);
    }
}

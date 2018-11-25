package services;

import database.CategoriesDB;
import database.ItemsDB;
import database.UserDB;
import java.util.List;
import model.Category;
import model.Item;
import model.User;

/**
 *
 * @author 687159
 */
public class InventoryService 
{
    private CategoriesDB catDB = new CategoriesDB();
    private ItemsDB itemDB = new ItemsDB();
    private UserDB userDB = new UserDB();
    
    public InventoryService() 
    {
        catDB = new CategoriesDB();
        itemDB = new ItemsDB();
        userDB = new UserDB();
    }
    
    public User getUsername(String username) throws Exception 
    {
        return userDB.getUser(username);
    }
    
    public Category getCategory(int categoryID) throws Exception
    {
        return catDB.getCategory(categoryID);
    }
    
    public List<Category> getAllCategories() throws Exception 
    {
        return catDB.getAll();
    }
    
    public List<Item> getAllItems() throws Exception 
    {
        return itemDB.getAll();
    }
    
    public int deleteItem(int itemID) throws Exception {
        Item itemSelected = itemDB.getItem(itemID);
        return itemDB.delete(itemSelected);
    }
    
    public int insertItem(int categoryID, String itemName, String username, double price) throws Exception {
        User user = new User();
        Category category = new Category();
        user.setUsername(username);
        category.setCategoryID(categoryID);
        
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setOwner(user);
        item.setCategory(category);
        return itemDB.insert(item);
    }
}

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
    
    private User user = new User();
    private Category category = new Category();
    private Item item = new Item();
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
    
    public Item getItem(int itemID) throws Exception
    {
        return itemDB.getItem(itemID);
    }
    
    public Item getItemByName(String itemName) throws Exception
    {
        return itemDB.getItemName(itemName);
    }
    
    public List<Category> getAllCategories() throws Exception 
    {
        return catDB.getAll();
    }
    
    public List<Item> getAllItems() throws Exception 
    {
        return itemDB.getAll();
    }
    
    public int update(int itemID, int categoryID, String itemName, double price) 
            throws Exception 
    {
        category = getCategory(categoryID);
        item = getItem(itemID);
        item.setItemID(itemID);
        item.setItemName(itemName);
        item.setPrice(price);
        item.setCategory(category);
        return itemDB.update(item);
    }
    
    public int deleteItem(int itemID) 
            throws Exception
    {
        Item itemSelected = itemDB.getItem(itemID);
        return itemDB.delete(itemSelected);
    }
    
    public int insertItem(int categoryID, String itemName, String username, double price) 
            throws Exception 
    {
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

package services;

import database.UserDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import servlets.AdminServlet;

/**
 *
 * @author 687159
 */
public class UserService 
{
    private UserDB userDB = new UserDB();

    public UserService() 
    {
        userDB = new UserDB();
    }
    
    public User loginService(String username, String password)
    {
        try
        {
            UserDB userDB = new UserDB();
            User user  = userDB.getUser(username);
            if (user.getPassword().equals(password))
            {
                return user;
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public User get(String username) throws Exception 
    {
        return userDB.getUser(username);
    }
    
    public List<User> getAll() throws Exception 
    {
        return userDB.getAll();
    }
    
    public int update(String username, String password, String email, String firstName, String lastName, boolean active, boolean isAdmin) throws Exception {
        User user = get(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(active);
        user.setIsAdmin(isAdmin);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        // do not allow the admin to be deleted
        if (deletedUser.getUsername().equals("admin")) {
            return 0;
        }
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String email, String firstName, String lastName, boolean active, boolean isAdmin) throws Exception {
        User user = new User(username, password, email, firstName, lastName, active, isAdmin);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);
        user.setIsAdmin(isAdmin);
        return userDB.insert(user);
    }
}

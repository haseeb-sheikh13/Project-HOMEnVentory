/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Item;
import model.User;
import services.InventoryService;
import services.UserService;

/**
 *
 * @author 687159
 */
public class InventoryServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        User user = new User();
        InventoryService is = new InventoryService();
        
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) 
        {
            try
            {
                String itemSelected = request.getParameter("itemSelected");
                int itemSelect = Integer.parseInt(itemSelected);
                Item item = is.getItem(itemSelect);
                session.setAttribute("itemSelected", item);
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            }
            catch(Exception ex)
            {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            }
            
        }
        
        List<Item> items = null;    
        try 
        {
            items = is.getAllItems(); 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("items", items);
        
        List<Category> categories = null;        
        try 
        {
            categories = is.getAllCategories(); 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("categories", categories);
        
        if(request.getParameter("admin") !=null)
        {
            try
            {
                String username = (String) session.getAttribute("username");
                UserService us = new UserService();
                user = us.get(username);
                if(user.getIsAdmin() == true)
                {
                    session.setAttribute("username", username);
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    return;
                }
                else
                {
                    request.setAttribute("adminM", "Unauthorized User.");
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                }            
            }
            catch(Exception ex)
            {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(request.getParameter("logout") !=null)
        {
            session.invalidate();
            request.setAttribute("logM", "You have been logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        
        String username = (String) session.getAttribute("username");
        
        String action = request.getParameter("action");
        String itemCategory = request.getParameter("itemCategory");
        String itemName = request.getParameter("itemName");
        String itemPrice = request.getParameter("itemPrice");
        
        int categoryID;
        int itemID;
        double price;
        
        User user = new User();
        Item item = new Item();
        
        InventoryService is = new InventoryService();
        try 
        {
            switch (action)
            {
                case "add":
                    if(!(itemCategory == null || itemCategory.equals("")) && !(itemName == null || itemName.equals("")) 
                            && !(itemPrice == null || itemPrice.equals("")))
                    {
                        List<Item> items = (List) session.getAttribute("items");
                        categoryID = Integer.parseInt(itemCategory);
                        Category category = is.getCategory(categoryID);
                        price = Double.parseDouble(itemPrice);
                     
                        UserService us = new UserService();
                        item = new Item(0, itemName, price);
                        item.setCategory(category);
                        
                        user.setUsername(username);
                        item.setOwner(us.get(username));
                        is.insertItem(categoryID, itemName, username, price);
                        items.add(item);
                        session.setAttribute("items", items);
                        request.setAttribute("addM", "New item added."); 
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("errorM", "Please fill in the required fields.");
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                    }
                    break;
                case "edit":
                    String hiddenCat = request.getParameter("hiddenCat");
                    
                    itemID = Integer.parseInt(hiddenCat);
                    categoryID = Integer.parseInt(itemCategory);
                    price = Double.parseDouble(itemPrice);
                    
                    Category category = is.getCategory(categoryID);
                    
                    item = is.getItem(itemID);
                    item.setItemID(itemID);
                    item.setCategory(category);
                    item.setItemName(itemName);
                    item.setPrice(price);
                    
                    is.update(itemID, categoryID, itemName, price);
                    request.setAttribute("editM", "Item has been updated."); 
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

                    break;
                case "delete":
                    String itemSelected = request.getParameter("itemSelected");
                    int itemSelect = Integer.parseInt(itemSelected);
                    is.deleteItem(itemSelect);
                    
                    request.setAttribute("deleteM", "Item has been deleted.");
                    response.sendRedirect("inventory");
                    break;
                default:
                    break;
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        }
    }
}

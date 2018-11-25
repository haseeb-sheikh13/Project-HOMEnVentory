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

/**
 *
 * @author 687159
 */
public class InventoryServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        
        InventoryService is = new InventoryService();
        List<Category> categories = null;        
        try 
        {
            categories = is.getAllCategories(); 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("categories", categories);
        
        List<Item> items = null;    
        try 
        {
            items = is.getAllItems(); 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("items", items);

        if(request.getParameter("logout") !=null)
        {
            session.invalidate();
            request.setAttribute("logM", "You have been logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        User user = new User();
        if(request.getParameter("admin") !=null)
        {
            if(user.getIsAdmin() == true)
            {
                session.invalidate();
                response.sendRedirect("admin");
                 
                return;
            }
            else
            {
                request.setAttribute("adminM", "Unauthorized User.");
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            }
            
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
        InventoryService is = new InventoryService();
        User user = new User();
        try 
        {
            switch (action)
            {
                case "add":
                    if(!(itemCategory == null || itemCategory.equals("")) && !(itemName == null || itemName.equals("")) 
                            && !(itemPrice == null || itemPrice.equals("")))
                    {
                        List<Item> items = (List) session.getAttribute("items");
                        int categoryID = Integer.parseInt(itemCategory);
                        Category category = is.getCategory(categoryID);
                        double price = Double.parseDouble(itemPrice);
                     
                        Item item = new Item(0, itemName, price);
                        item.setCategory(category);
                        
                        user.setUsername(username);
                        item.setOwner(user);
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
                case "delete":
                    String itemSelected = request.getParameter("itemSelected");
                    int itemSelect = Integer.parseInt(itemSelected);
                    is.deleteItem(itemSelect);
                    
                    request.setAttribute("deleteM", "Item has been deleted.");
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response); 
                    break;
                case "admin":
                    if(user.getIsAdmin() == false)
                    {
                        request.setAttribute("adminM", "Unauthorized User.");
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response); 
                    }
                    else
                    {
                        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    }
                    break;
                case "logout":
                    request.setAttribute("logoutM", "You have been logged out.");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

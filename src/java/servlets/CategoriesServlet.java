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
import model.User;
import services.CategoryService;
import services.InventoryService;
import services.UserService;

/**
 *
 * @author 687159
 */
public class CategoriesServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        
        User user = new User();
        
        UserService us = new UserService();
        InventoryService is = new InventoryService();
        CategoryService cs = new CategoryService();
        
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) 
        {
            try
            {
                String categorySelected = request.getParameter("categorySelected");
                int categorySelect = Integer.parseInt(categorySelected);
                Category category = cs.getCategory(categorySelect);
                session.setAttribute("categorySelected", category);
                getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
            }
            catch(Exception ex)
            {
                Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
                getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
            }
        }
        
        List<Category> categories = null;        
        try 
        {
            categories = is.getAllCategories(); 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("categories", categories);
       
        try
        {
            String username = (String) session.getAttribute("username");
            user = us.get(username);
            if(request.getParameter("admin") !=null)
            {
                if(user.getIsAdmin() == true)
                {
                    session.setAttribute("username", username);
                    response.sendRedirect("admin");
                    return;
                }
                else
                {
                    request.setAttribute("adminM", "Unauthorized User.");
                    response.sendRedirect("categories");
                }
            }
            else if(request.getParameter("inventory") !=null)
            {
                session.setAttribute("username", username);
                response.sendRedirect("inventory");
                return;
            }
            else if(request.getParameter("logout") !=null)
            {
                session.invalidate();
                request.setAttribute("logM", "You have been logged out.");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String action = request.getParameter("action");
        String categoryName = request.getParameter("categoryName");
        int categoryID;
        Category category = new Category();
        CategoryService cs = new CategoryService();
        try 
        {
            switch (action)
            {
                case "add":
                    if(!(categoryName == null || categoryName.equals("")))
                    {
                        List<Category> categories = (List) session.getAttribute("categories");
                        category = new Category(0, categoryName);
                        category.setCategoryName(categoryName);
                        cs.insertCategory(categoryName);
                        categories.add(category);
                        session.setAttribute("categories", categories);
                        request.setAttribute("addM", "New cateogry added."); 
                        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("errorM", "Please fill in the required fields.");
                        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
                    }
                    break;
                case "edit":
                    String hiddenCat = request.getParameter("hiddenCat");
                    categoryID = Integer.parseInt(hiddenCat);
                    
                    category = cs.getCategory(categoryID);
                    category.setCategoryID(categoryID);
                    category.setCategoryName(categoryName);
                    cs.update(categoryID, categoryName);
                    request.setAttribute("editM", "Category has been updated."); 
                    getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
        }
    }
}

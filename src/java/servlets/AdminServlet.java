/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import services.UserService;

/**
 *
 * @author 687159
 */
public class AdminServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) 
        {
            String usernameSelected = request.getParameter("usernameSelected");
            try 
            {
                User user = us.get(usernameSelected);
                request.setAttribute("usernameSelected", user);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        List<User> users = null;        
        try 
        {
            users = us.getAll(); 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("users", users);
        
        if(request.getParameter("inventory") !=null)
        {
            response.sendRedirect("inventory"); 
            return;
        }
        
        if(request.getParameter("categories") !=null)
        {
            response.sendRedirect("categories"); 
            return;
        }
        
        if(request.getParameter("logout") !=null)
        {
            session.invalidate();
            request.setAttribute("logM", "You have been logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        boolean active = false;
        boolean isAdmin = false;

        UserService us = new UserService();

        try 
        {
            switch(action)
            {
                case "add":
                    if(!(username == null || username.equals("")) && !(password == null || password.equals("")) 
                        && !(email == null || email.equals("")) && !(firstName == null || firstName.equals("")) 
                            && !(lastName == null || lastName.equals("")))
                    {                   
                        us.insert(username, password, email, firstName, lastName, active, isAdmin);
                        request.setAttribute("addM", "New User added.");
                        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);   
                    }
                    else
                    {
                        request.setAttribute("errorM", "Please enter the required fields.");
                        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    }
                    break;
                case "edit":
                    String activeStatus = request.getParameter("activeStatus");
                    active = Boolean.valueOf(activeStatus);
                    us.update(username, password, email, firstName, lastName, active, isAdmin);
                    request.setAttribute("edit", "User has been updated.");
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response); 
                    break;
                case "delete":
                    String usernameSelected = request.getParameter("usernameSelected");
                    us.delete(usernameSelected);
                    request.setAttribute("deleteM", "User has been deleted.");
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response); 
                    break;
                default:
                    break;
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

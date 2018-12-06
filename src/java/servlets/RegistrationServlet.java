/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

/**
 *
 * @author 687159
 */
public class RegistrationServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        if(request.getParameter("login") !=null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
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
                case "register":
                    if(!(username == null || username.equals("")) && !(password == null || password.equals("")) 
                        && !(email == null || email.equals("")) && !(firstName == null || firstName.equals("")) && !(lastName == null || lastName.equals("")))
                    {                   
                        us.insert(username, password, email, firstName, lastName, active, isAdmin);
                        request.setAttribute("registerM", "You have been registered. Please go back to login.");
                        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);   
                    }
                    else
                    {
                        request.setAttribute("errorM", "Please enter the required fields.");
                        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
                    }
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

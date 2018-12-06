/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        if(request.getParameter("registration") !=null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession();
 
        UserService us = new UserService();
        User user = us.loginService(username, password);  
        if(!(username == null || username.equals("")) && !(password == null || password.equals("")))
        {
                
            if(user != null)
            {
                if((user.getActive() == true  && user.getIsAdmin() == true))
                {
                    session.setAttribute("username", username);
                    response.sendRedirect("admin");
                }
                else if(user.getActive() == true  && user.getIsAdmin() == false)
                {
                    session.setAttribute("username", username);
                    response.sendRedirect("inventory");
                }
                else
                {
                    request.setAttribute("inactiveM", "Your account has been deactivated, please register a new account.");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);       
                }   
            }
            else
            {
                request.setAttribute("errorM", "User does not exist.");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
        else
        {
            request.setAttribute("errorM", "Please enter your username or password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
    }
}

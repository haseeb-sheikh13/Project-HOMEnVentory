<%-- 
    Document   : login
    Created on : Nov 22, 2018, 12:23:39 PM
    Author     : 687159
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        <%@include file="/WEB-INF/CSS/loginCSS.css"%>
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOMEnVentory</title>
    </head>
    <body background="bg1.jpg">
        <div class="header">
            <ul>
                <li><h1>HOMEnVentory</h1></li>
            </ul>
        </div>
        
        <form method="POST" action="login">
            <div class="loginForm">
                <h2>Login</h2>
                <label for="username"><b>Username:</b></label><br>
                <input type="text" placeholder="Enter Username" name="username" required><br>

                <label for="password"><b>Password:</b></label><br>
                <input type="password" placeholder="Enter Password" name="password" required><br>

                <button type="submit">Login</button>
                <h3>${errorM}</h3>
                <h3>${inactiveM}</h3>
                <h3>${logM}</h3>
            </div>
            
            <div class="registrationForm" style="background-color:#f1f1f1">
                <span class="psw">Don't have an account? <a href="login?registration">Register here!</a></span>
            </div>
        </form>
    </body>
</html>

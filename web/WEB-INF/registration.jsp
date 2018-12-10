<%-- 
    Document   : registration
    Created on : Nov 26, 2018, 11:56:40 AM
    Author     : 687159
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <style>
        <%@include file="/WEB-INF/CSS/registrationCSS.css"%>
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOMEnVentory</title>
    </head>
    <body>
        <ul>
            <li><h1>HOMEnVentory</h1></li>
            <li><a href="registration?login">Login</a></li>
        </ul>
        <h2>Registration</h2>
        <form method="POST" action="registration">
            <h3>Create your account</h3>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label for="username"><b>Username:</b></label><br>
            <input type="text" name="username"><br>

            <label for="password"><b>Password:</b></label><br>
            <input type="text" name="password"><br>

            <label for="email"><b>Email:</b></label><br>
            <input type="text" name="email"><br>

            <label for="firstName"><b>First Name:</b></label><br>
            <input type="text" name="firstName"><br>

            <label for="lastName"><b>Last Name:</b></label><br>
            <input type="text" name="lastName"><br>
            
            <input type="hidden" name="action" value="register">
            <button type="submit">Register</button>
            
            <div class="notifications">
                ${registerM}
                ${errorM}
            </div>
        </form>
    </body>
</html>

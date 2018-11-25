<%-- 
    Document   : login
    Created on : Nov 22, 2018, 12:23:39 PM
    Author     : 687159
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Login</h2>
        <form method="POST" action="login">
            Username: <input type="text" required name="username"><br>
            Password: <input type="password" required name="password"><br>
            <input type="submit" value="Submit" name="Login">
        </form>
        ${errorM}
        ${inactiveM}
        ${logM}
    </body>
</html>

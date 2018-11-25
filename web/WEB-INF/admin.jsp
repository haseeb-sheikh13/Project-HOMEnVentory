<%-- 
    Document   : admin
    Created on : Nov 24, 2018, 8:55:47 PM
    Author     : 687159
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<style>
        table, th, td 
        {
            border: 1px solid black;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Assignment 3 - HomeInventory</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        
        <h3>Menu</h3>
        <ul>
            <li><a href="admin?inventory">Inventory</a></li>
            <li><a href="admin?refresh">Refresh</a></li>
            <li><a href="admin?logout">Logout</a></li>
        </ul>

        <h2>Manage Users</h2>      
            <table style="width:50%">
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
                <c:forEach var="usr" items="${users}">
                    <tr>
                        <td>${usr.username}</td>
                        <td>${usr.firstName}</td>
                        <td>${usr.lastName}</td>
                        <td>
                            <form method="POST" action="admin">
                                <input type="submit" value="Delete">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="usernameSelected" value="${usr.username}">
                            </form>
                        </td>
                        <td>
                            <form method="GET" action="admin">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="usernameSelected" value="${usr.username}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        
        <c:if test="${usernameSelected == null}">
            <h2>Add User</h2>
            <form method="POST" action="admin">
                Username: <input type="text" name="username"><br>
                Password: <input type="text" name="password"><br>
                Email: <input type="text" name="email"><br>
                First Name: <input type="text" name="firstName"><br>
                Last Name: <input type="text" name="lastName"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${usernameSelected != null}">
            <h2>Edit User</h2>
            <form method="POST" action="admin">
                Username: <input type="text" name="username" value="${usernameSelected.username}" readonly><br>
                Password: <input type="password" name="password" value="${usernameSelected.password}"><br>
                Email: <input type="text" name="email" value="${usernameSelected.email}"><br>
                First Name: <input type="text" name="firstName" value="${usernameSelected.firstName}"><br>
                Last Name: <input type="text" name="lastName" value="${usernameSelected.lastName}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
        ${addM}
        ${errorM}
        ${editM}
        ${deleteM}
    </body>
</html>

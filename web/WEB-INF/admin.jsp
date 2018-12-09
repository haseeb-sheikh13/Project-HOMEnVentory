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
        <%@include file="/WEB-INF/CSS/adminCSS.css"%>
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOMEnVentory</title>
    </head>
    <body>
        <ul>
            <li><h1>HOMEnVentory</h1></li>
            <li><a href="admin?inventory">Inventory</a></li>
            <li><a href="admin?categories">Categories</a></li>
            <li><a href="admin?refresh">Refresh</a></li>
            <li><a href="admin?logout">Logout</a></li>
        </ul>
        <div class="usersTable">
        <h2>Welcome, ${username}</h2>
        <h2>Manage Users</h2>      
            <table>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Active</th>
                    <th>Admin</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="usr" items="${users}">
                    <tr>
                        <td>${usr.username}</td>
                        <td>${usr.firstName}</td>
                        <td>${usr.lastName}</td>
                        <td>${usr.active}</td>
                        <td>${usr.isAdmin}</td>
                        <td>
                            <form method="GET" action="admin">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="usernameSelected" value="${usr.username}">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="admin">
                                <input type="submit" value="Delete">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="usernameSelected" value="${usr.username}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        <h3>${deleteM}</h3>
        <h3>${errorDeleteM}</h3>
        </div>
        
        <div class="userForms">
        <c:if test="${usernameSelected == null}">
            <h2>Add User</h2>
            <form method="POST" action="admin">
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
                
                <label for="adminStatus"><b>Admin Status: </b></label><br>
                <select name="adminStatus">
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select><br>
                
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${usernameSelected != null}">
            <h2>Edit User</h2>
            <form method="POST" action="admin">
                <label for="username"><b>Username:</b></label><br>
                <input type="text" name="username" value="${usernameSelected.username}" readonly><br>
                
                <label for="password"><b>Password:</b></label><br>
                <input type="text" name="password" value="${usernameSelected.password}"><br>
                
                <label for="email"><b>Email:</b></label><br>
                <input type="text" name="email" value="${usernameSelected.email}"><br>
                
                <label for="firstName"><b>First Name:</b></label><br>
                <input type="text" name="firstName" value="${usernameSelected.firstName}"><br>
                
                <label for="lastName"><b>Last Name:</b></label><br>
                <input type="text" name="lastName" value="${usernameSelected.lastName}"><br>
                
                <label for="activeStatus"><b>Active Status: </b></label><br>
                <select name="activeStatus">
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select><br>
                
                <label for="adminStatus"><b>Admin Status: </b></label><br>
                <select name="adminStatus">
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
            <h3>${addM}</h3>
            <h3>${editM}</h3>
            <h3>${errorM}</h3>
        </div>
        
        
    </body>
</html>

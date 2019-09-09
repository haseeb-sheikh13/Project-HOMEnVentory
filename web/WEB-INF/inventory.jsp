<%-- 
    Document   : inventory
    Created on : Nov 26, 2018, 11:42:28 AM
    Author     : 687159
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <style>
        <%@include file="/WEB-INF/CSS/inventoryCSS.css"%>
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOMEnVentory</title>
    </head>
    <body>
        <ul>
            <li><h1>HOMEnVentory</h1></li>
            <li><a href="inventory?admin">Admin</a><p>${unauthorizedM}</p></li>
            <li><a href="inventory?categories">Categories</a></li>
            <li><a href="inventory?refresh">Refresh</a></li>
            <li><a href="inventory?logout">Logout</a></li>
        </ul>
            
            
        <div class="inventoryForm">
            <h2>Inventory for ${username}</h2>
            
            
            <c:if test="${searchItem == null}">
                <table>

                    <tr>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="itms" items="${items}">
                    <tr>
                        <td>${itms.category.categoryName}</td>
                        <td>${itms.itemName}</td>
                        <td><fmt:formatNumber value="${itms.price}" type="currency"/></td>
                        <td>
                            <form method="GET" action="inventory">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="itemSelected" value="${itms.itemID}">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="inventory">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="itemSelected" value="${itms.itemID}">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <form method="GET" action="inventory">
                    Search Item: <input type="text" name="searchItem"><input type="submit" value="Search">
                    <input type="hidden" name="action" value="search">
                </form>
            </c:if>
            
            <c:if test="${searchItem != null}">
                <table>
                    <tr>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="searched" items="${searchedItems}">
                    <tr>
                        <td>${searched.category.categoryName}</td>
                        <td>${searched.itemName}</td>
                        <td><fmt:formatNumber value="${searched.price}" type="currency"/></td>
                        <td>
                            <form method="GET" action="inventory">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="itemSelected" value="${searched.itemID}">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="inventory">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="itemSelected" value="${searched.itemID}">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </c:if>
            
            <c:if test="${itemSelected == null}">
                <h2>Add Item</h2>
                <form method="POST" action="inventory">
                    <label for="itemCategory"><b>Category: </b></label><br>
                    <select name="itemCategory">
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat.categoryID}">${cat.categoryName}</option>
                        </c:forEach>
                    </select><br>
                    <label for="itemName"><b>Name:</b></label><br>
                    <input type="text" name="itemName"><br>
                    <label for="itemPrice"><b>Price:</b></label><br>
                    <input type="text" name="itemPrice"><br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Add Item">
                </form>
            </c:if>
            <c:if test="${itemSelected != null}">
                <h2>Edit Item</h2>
                <form method="POST" action="inventory">
                    <input type="hidden" name="hiddenCat" value="${itemSelected.itemID}"/>
                    <label for="itemCategory"><b>Category: </b></label><br>
                    <select name="itemCategory">
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat.categoryID}">${cat.categoryName}</option>
                        </c:forEach>
                    </select><br>
                    <label for="itemName"><b>Name:</b></label><br>
                    <input type="text" name="itemName" value="${itemSelected.itemName}"><br>
                    <label for="itemPrice"><b>Price:</b></label><br>
                    <input type="text" name="itemPrice" value="${itemSelected.price}"><br>
                    <input type="hidden" name="action" value="editUser">
                    <input type="submit" value="Edit Item">
                </form>
            </c:if>
                <h3>${addM}</h3>
                <h3>${errorM}</h3>
                <h3>${deleteM}</h3>
        </div>
        
        <div class="usersForm">
            <table>
                <h2>${username}'s account</h2>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Edit</th>
                </tr>
                <tr>
                    <td>${usersInfo.username}</td>
                    <td>${usersInfo.password}</td>
                    <td>${usersInfo.email}</td>
                    <td>${usersInfo.firstName}</td>
                    <td>${usersInfo.lastName}</td>
                    <td>
                        <form method="GET" action="inventory">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="editUser">
                            <input type="hidden" name="usernameSelected" value="${usersInfo.username}">
                        </form>
                    </td>
                </tr>
            </table>

        <c:if test="${usernameSelected != null}">
            <h2>Edit User</h2>
            <form method="POST" action="inventory">
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

                <input type="hidden" name="action" value="editUser">
                <input type="submit" value="Save">
            </form>
        </c:if>
            <h3>${editUserM}</h3>
        </div>
    </body>
</html>
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
        <link rel="stylesheet" type="text/css" href="/WEB-INF/CSS/HOMEnVentoryCSS.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOMEnVentory</title>
    </head>
    <body>
        <ul>
            <li><h1>HOMEnVentory</h1></li>
            <li><a href="inventory?admin">Admin</a></li>
            <li><a href="inventory?refresh">Refresh</a></li>
            <li><a href="inventory?logout">Logout</a></li>
        </ul>
        ${adminM}
        <h2>Inventory for ${username}</h2>
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
                        <option value="${cat.categoryID}">${itemSelected.category.categoryName}</option>
                    </c:forEach>
                </select><br>
                
                <label for="itemName"><b>Name:</b></label><br>
                <input type="text" name="itemName" value="${itemSelected.itemName}"><br>
              
                <label for="itemPrice"><b>Price:</b></label><br>
                <input type="text" name="itemPrice" value="${itemSelected.itemPrice}"><br>
                
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Edit Item">
            </form>
        </c:if>
        ${addM}
        ${errorM}
        ${deleteM}
    </body>
</html>
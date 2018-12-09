<%-- 
    Document   : categories
    Created on : Nov 27, 2018, 12:29:49 PM
    Author     : 687159
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <style>
        <%@include file="/WEB-INF/CSS/categoriesCSS.css"%>
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 - Final Project - HOMEnVentory</title>
    </head>
    <body>
        <ul>
            <li><h1>HOMEnVentory</h1></li>
            <li><a href="categories?admin">Admin</a></li>
            <li><a href="categories?inventory">Inventory</a>
            <li><a href="categories?refresh">Refresh</a></li>
            <li><a href="categories?logout">Logout</a></li>
        </ul>
        
        <div class="categoryTable">
        <h2>Categories</h2>      
            <table>
                <tr>
                    <th>Category</th>
                    <th>Edit</th>
                </tr>
                <c:forEach var="cat" items="${categories}">
                <tr>
                    <td>${cat.categoryName}</td>
                    <td>
                        <form method="GET" action="categories">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="categorySelected" value="${cat.categoryID}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
        
        <div class="categoriesForms">
        <c:if test="${categorySelected == null}">
            <h2>Add Category</h2>
            <form method="POST" action="categories">
                <label for="categoryName"><b>Category:</b></label><br>
                <input type="text" name="categoryName"><br>
                
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add Category">
            </form>
        </c:if>
        <c:if test="${categorySelected != null}">
            <h2>Edit Category</h2>
            <form method="POST" action="categories">
                <input type="hidden" name="hiddenCat" value="${categorySelected.categoryID}"/>
                
                <label for="categoryName"><b>Category:</b></label><br>
                <input type="text" name="categoryName" value="${categorySelected.categoryName}"><br>
                
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Edit Category">
            </form>
        </c:if>
            <h3>${addM}</h3>
            <h3>${errorM}</h3>
            <h3>${editM}
        </div>
    </body>
</html>

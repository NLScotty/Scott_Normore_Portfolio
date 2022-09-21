<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
    </head>
    <body>
        <form action="item" method="post">
            <h1>Edit Form</h1>
            <input type="hidden" name="action" value="edit">
            <label for ="eId">Item Id:</label>
            <input type ="text" name="eId" value="${eItem.itemId}">
            <label for="eName">Item Name: </label>
            <input type="text" name="eName" id="eName" value="${eItem.itemName}">
            <label for="ePrice">Item Price: </label>
            <input type="text" name="ePrice" id="ePrice" value="${eItem.price}">
            <label for="eCategory">Item Category: </label>
            <select name="eCategory" id="eCategory">
                <c:forEach items="${categories}" var="category">
                    <c:choose>
                        <c:when test = "${eItem.category.categoryId eq category.categoryId}">
                            <option value="${category.categoryId}" selected="true">${category.categoryName}</option> 
                        </c:when>
                        <c:otherwise>
                            <option value="${category.categoryId}">${category.categoryName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <button type="submit">Update</button>
        </form>
        <h1>Item Table</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Item ID</th>
                    <th>Item Name</th>
                    <th>Item Price</th>
                    <th>Item Category</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.itemId}</td>
                        <td>${item.itemName}</td>
                        <td>${item.price}</td>
                        <td>${item.category.categoryName}</td>
                        <td><a href="item?action=edit&id=${item.itemId}">Edit</a></td>
                        <td><a href="item?action=delete&id=${item.itemId}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="item" method="post">
            <h1>Add Form</h1>
            <input type="hidden" name="action" value="add">
            <label for="aName">Item Name: </label>
            <input type="text" name="aName" id="aName">
            <label for="aPrice">Item Price: </label>
            <input type="text" name="aPrice" id="aPrice">
            <label for="aCategory">Category: </label>
            <select name="aCategory" id="aCategory">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select>
            <button type="submit">Add</button>
        </form>
        <p>${message}</p>
    </body>
    <a href="home">Back to Home Page</a>
</html>

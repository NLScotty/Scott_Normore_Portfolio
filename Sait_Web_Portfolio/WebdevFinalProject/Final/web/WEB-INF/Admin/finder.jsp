<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
    </head>
    <body>
        <form action="finder" method="post">
            <h1>Search Form</h1>
            <label for="value">Item Name: </label>
            <input type="text" name="value" id="value">
            <button type="submit">Search</button>
        </form>
        <table class="table">
            <thead>
                <tr>
                    <th>Item ID</th>
                    <th>Item Name</th>
                    <th>Item Price</th>
                    <th>Item Category</th>
                    <th>Item Owner</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.itemId}</td>
                        <td>${item.itemName}</td>
                        <td>${item.price}</td>
                        <td>${item.category.categoryName}</td>
                        <td>${item.owner.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="home">Back to Home Page</a>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        
    </head>
    <body>
        <form action="category" method="post">
            <h1>Edit Form</h1>
            <input type="hidden" name="action" value="edit">
            <label for="eId">Category Id: </label>
            <input type="text" name="eId" id="eId" value="${category.categoryId}">
            <label for="eName">Category Name: </label>
            <input type="text" name="eName" id="eFName" value="${category.categoryName}">
            <button type="submit">Update</button>
        </form>
        <h1>Category Table</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>${category.categoryId}</td>
                        <td>${category.categoryName}</td>
                        <td><a href="category?action=edit&id=${category.categoryId}">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="category" method="post">
            <h1>Add Form</h1>
            <input type="hidden" name="action" value="add">
            <label for="aName">Category Name: </label>
            <input type="text" name="aName" id="aName">
            <button type="submit">Add</button>
        </form>
        <a href="home">Back to Home Page</a>
        <p>${message}</p>
    </body>
</html>
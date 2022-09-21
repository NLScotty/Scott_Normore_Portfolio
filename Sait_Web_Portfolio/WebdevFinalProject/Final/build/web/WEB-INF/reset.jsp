<%-- 
    Document   : reset
    Created on : Apr. 22, 2022, 9:28:44 a.m.
    Author     : Scott
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
    </head>
    <body>
        <h1>Notes App</h1>
        <h2>Reset Password</h2>
        <form action="forgot" method="post">
            <input type="hidden" name="uuid" value="${uuid}">
            New Password: <input type="text" name="password">
            <input type="submit" value="Submit">
        </form>
        <p>${message}</p>
    </body>
</html>

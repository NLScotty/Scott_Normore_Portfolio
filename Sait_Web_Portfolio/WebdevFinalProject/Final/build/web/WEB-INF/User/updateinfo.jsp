<%-- 
    Document   : accountedit
    Created on : Apr. 16, 2022, 3:37:18 p.m.
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
        <form action="account" method="post">
            <h1>ACCOUNT PAGE</h1>
            <h2>Update Information</h2>
            <input type="hidden" name="action" value="updateprofile">
            First Name: <input type="text" name="fname"><br>
            Last Name: <input type="text" name="lname"><br>
            <input type="submit" value="updateInfo">
        </form>
    </body>
</html>

<%-- 
    Document   : register
    Created on : Apr. 18, 2022, 1:45:17 p.m.
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
        <h1>Login</h1>
        <form action="register" method="post">
            First name: <input type="text" name="fname" value=${fName}><br>
            Last name: <input type="text" name="lname" value=${lName}><br>
            Email: <input type="text" name="email" value=${Email}><br>
            Password: <input type="password" name="password"><br>
            Confirm password: <input type="password" name="confirmpassword"> <br>
            <input type="submit" value="Sign up">
        </form>
        <p>${message}</p>
    </body>
</html>

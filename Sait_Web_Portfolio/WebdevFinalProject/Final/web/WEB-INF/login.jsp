<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
    </head>
    <body>
        <h1>n-Ventory</h1>
        <h2>Login</h2>
        <form action="login" method="post">
            email: <input type="text" name="email" value="${email}"><br>
            password: <input type="password" name="password"><br>
            <input type="submit" value="Sign in">
        </form>
        <a href="forgot">Forgot Password? Click here!</a> 
        <br>
        <a href="register">Don't have an account? Click here!</a>
    </body>
</html>
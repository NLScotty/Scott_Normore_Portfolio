<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
    </head>
    <body>
        <h1>n-Ventory</h1>
        <h2>Authorization</h2>
        <p>Check your inbox for the two factor authorization code</p>
        <form action="authorize" method="post">
            <input type="text" name="code"><br>
            <input type="submit" value="Authorize">
        </form>
        <p>${message}</p>
    </body>
</html>

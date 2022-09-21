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
            <h2>Update Password</h2>
            <input type="hidden" name="action" value="updatepassword">
            Confirm Old Password: <input type="password" name="oldpassword"><br>
            Enter New Password: <input type="password" name="newpassword"><br>
            <input type="submit" value="updatePassword">
            <p>${message}</p>
        </form>
    </body>
</html>

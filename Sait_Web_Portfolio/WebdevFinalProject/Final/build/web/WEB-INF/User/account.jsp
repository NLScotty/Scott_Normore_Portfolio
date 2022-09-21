<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nVentory</title>
    </head>
    <body>
        <h1>ACCOUNT PAGE</h1>
            <h2>Current Information</h2>
                <p>First Name: ${fname} <a href="account?action=updateprofile">Click here to edit</a></p>
                <p>Last Name: ${lname} <a href="account?action=updateprofile">Click here to edit</a></p>
                <p>Password: ********* <a href="account?action=updatepassword">Click here to edit</a></p>
                <p>Two Factor Authorization Enabled: ${twoenabled} <a href="account?action=toggle2fa">Click here to toggle</a> </p>
                <p><a href="account?action=deactivate">Click here to deactivate your account</a></p>
                <br>
                <br>
                <a href="home">Back to Home Page</a>
    </body>
</html>

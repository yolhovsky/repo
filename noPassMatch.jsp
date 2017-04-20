<%-- 
    Document   : noPassMatch
    Created on : Apr 6, 2017, 2:44:18 PM
    Author     : mariazots
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error!</title>
    </head>
    <body>
        
        <form name="home" action="index.html">
            <h1>Passwords for user <%= session.getAttribute("user1") %> do not match.</h1>
            <input type="submit" value="Go Back" name="back" />
        </form>
    </body>
</html>

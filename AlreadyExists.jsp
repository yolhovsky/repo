<%-- 
    Document   : AlreadyExists
    Created on : Feb 1, 2017, 2:26:48 PM
    Author     : mariazots
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="back" action="index.html">
            <h1>Username <%= session.getAttribute("user1") %> not available</h1>
            <input type="submit" value="goBack" name="home" />
        </form>
        
    </body>
</html>

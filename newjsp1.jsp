
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>      
        
        <form name="firstexisting" action="FirstExisting">
            <h1> logged in as : <%= session.getAttribute("user1") %> </h1>
            <% session.setAttribute("user1", session.getAttribute("user1"));                 %>
            <input type="submit" value="Continue to enter info" name="EnterDataButton" />
        </form>
    </body>
</html>

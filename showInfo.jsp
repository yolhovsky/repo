
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>your info</title>
    </head>
    <body>
        <form name="findMatch" action="FindMatch">
        
        <h1>username : <%= session.getAttribute("user1") %></h1><br>
        <h1>email : <%= session.getAttribute("email") %></h1><br>
        <h1>zip : <%= session.getAttribute("zip") %></h1><br>
        
        <table border="1">
            
            <tbody>
                <tr>
                    <td>skill 1 Owned : </td>
                    <td><%= session.getAttribute("ownedSkill1") %></td>  <!-- "skillsowned1" -->                  
                </tr>
                <tr>
                    <td>skill 1 wanted : </td>
                    <td><%= session.getAttribute("wantedSkill1") %></td>  <!-- "skillswanted1" -->
                </tr>
                <tr>
                    <td>skill 2 Owned : </td>
                    <td><%= session.getAttribute("ownedSkill2") %></td>
                </tr>
                <tr>
                    <td>skill 2 wanted : </td>
                    <td><%= session.getAttribute("wantedSkill2") %></td>
                </tr>
                <tr>
                    <td>skill 3 Owned : </td>
                    <td><%= session.getAttribute("ownedSkill3") %></td>
                </tr>
                <tr>
                    <td>skill 3 wanted : </td>
                    <td><%= session.getAttribute("wantedSkill3") %></td>
                </tr>
                <tr>
                    <td>skill 4 Owned :</td>
                    <td><%= session.getAttribute("ownedSkill4") %></td>
                </tr>
                <tr>
                    <td>skill 4 wanted :</td>
                    <td><%= session.getAttribute("wantedSkill4") %></td>
                </tr>
                <tr>
                    <td>skill 5 Owned :</td>
                    <td><%= session.getAttribute("ownedSkill5") %></td>
                </tr>
                <tr>
                    <td>skill 5 wanted :</td>
                    <td><%= session.getAttribute("wantedSkill5") %></td>
                </tr>
            </tbody>
        </table>          
                <input type="submit" value="Find matches" name="findmatches" />
         </form>
                <form name="edit" action="updateUser.jsp">
                    <input type="submit" value="Edit " name="edit" />
                </form>
    </body>
</html>

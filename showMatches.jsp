<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import = "java.util.ArrayList" %>
<%@page  import = "mypack.ShowMatch" %>
<%@page  import = "mypack.FindMatch" %>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            out = pageContext.getOut();
            PrintWriter pw = response.getWriter();
            out.write("<p> Number of matches found: "+ request.getAttribute("NumofMatches")+ "<p/>"); %>   
            
            
            
            <table border="5">
                <thead>
                    <tr>
                         <th>Name</th> 
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int i = 0; i<3; i++){
                          if(request.getAttribute("list"+i) != null && request.getAttribute("list1"+i)!=null){ %>
                    <tr>
                        <% out.write("<td> "+ request.getAttribute("list"+i) + "</td>"); %>
                        <% out.write("<td> "+ request.getAttribute("list1"+i) + "</td>"); %>
                        
                        <% 
                             }
                          }
                        %>         
                    </tr>
                </tbody>
            </table>

                        <form name="Edit" action="showInfo.jsp">
                            
                            <input type="submit" value="Back to My Page" name="home" />
                        </form>
    </body>
    
</html>

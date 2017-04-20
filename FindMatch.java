package mypack;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FindMatch extends HttpServlet {
         public ArrayList Rows = new ArrayList();
         Connection con = null;
    public void init(ServletConfig config) throws ServletException
    {
        String URL = "jdbc:postgresql://localhost:5432/midterm";
        String USERNAME = "postgres";
        String PASSWORD = "<password>";
        
        // Load the PostgreSQL driver
        try 
        {
              Class.forName("org.postgresql.Driver");
              con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (ClassNotFoundException ex)
        {
               System.err.println("ClassNotFoundException: " + ex.getMessage());
               throw new ServletException("Class not found Error");
        }
        catch (SQLException ex)
        {
               System.err.println("SQLException: " + ex.getMessage());
        }
    }
         protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html");
        
        String name = new String();
        HttpSession session = request.getSession(); 
        name = session.getAttribute("user1").toString();     
        String zip = null;
        String email = null;
        PrintWriter out = response.getWriter();
  
        PreparedStatement findMatches = null;
        ResultSet rs = null;
        try {
            findMatches = con.prepareStatement("select distinct a.username, a.email\n" +
                                                "from flyingcars.fullinfo a\n" +
                                                "join flyingcars.fullinfo b\n" +
                                                "on a.skillsowned = b.skillswanted\n" +
                                                "and a.skillswanted = b.skillsowned\n" +
                                                "where a.zip = b.zip and b.username = ?" +
                                                "and a.username != b.username\n");
            findMatches.setString(1, name);
            rs = findMatches.executeQuery();
            
          int i=0;
              while(rs.next()){
                request.setAttribute("list"+i, rs.getString("username"));
                request.setAttribute("list1"+i, rs.getString("email"));
                i++;       
             }

       request.setAttribute("NumofMatches", i); 
       
        request.getRequestDispatcher("showMatches.jsp").forward(request,response);
        }
         
        catch (SQLException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
        //response.sendRedirect("showMatches.jsp");
    }
         
  }


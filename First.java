package mypack;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class First extends HttpServlet {
    
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
        String name = new String();
        String password = new String();
        name=request.getParameter("user1");
        password=request.getParameter("pass");
        HttpSession session = request.getSession();       
        session.setAttribute("user1", name);  
        String hashedPass = MD5(password);
        session.setAttribute("pass", hashedPass);
         
        
        
        PreparedStatement checkUser = null;
        ResultSet rs = null;
        try {
            
            checkUser = con.prepareStatement("SELECT username from flyingcars.user WHERE username = ? AND password = ? ");
            checkUser.setString(1, name);
            checkUser.setString(2, hashedPass);
            rs = checkUser.executeQuery();
        
        if(!rs.next())      
            response.sendRedirect("notFound.jsp");
        else{
           
           response.sendRedirect("newjsp1.jsp");
        }
        }catch (SQLException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //HASH METHOD
          public String MD5(String md5) {
        try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
          } catch (java.security.NoSuchAlgorithmException e) {
          }
         return null;
}
}

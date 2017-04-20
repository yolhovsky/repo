package mypack;



import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class NewUser extends HttpServlet {
    Connection con = null;
    PreparedStatement insertUser = null;
    PreparedStatement checkUser = null;
    ResultSet rs = null;
    
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
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            int result = 0; //returns how many rows got affected
             try{ 
                    String name = new String();
                    String password = new String();
                    String password2 = new String();
                    String email = new String();
                    String zip = new String();
                    HttpSession session = request.getSession();
                    checkUser = con.prepareStatement( "SELECT username from flyingcars.user WHERE username = ?");
                    
                    name = request.getParameter("user1");                   
                    checkUser.setString(1, name);                   
                    rs = checkUser.executeQuery();
                    if(rs.next()){
                        session.setAttribute("user1", name);
                        response.sendRedirect("AlreadyExists.jsp");
                    }
                    else{
                           password = request.getParameter("pass1");
                           password2 = request.getParameter("pass2");
                      if(password.equals(password2)){
                         insertUser = con.prepareStatement( "INSERT INTO flyingcars.user (username, password) VALUES (?, ?)");
                         name = request.getParameter("user1");
                         email = request.getParameter("email");
                         zip = request.getParameter("zip");        
                         insertUser.setString(1, name);
                         String hashedPassword = MD5(password);
                         insertUser.setString(2, hashedPassword);
                         result = insertUser.executeUpdate();
                         session.setAttribute("user1", name);
                         session.setAttribute("email", email);
                         session.setAttribute("zip", zip);
                         response.sendRedirect("userInfo.jsp");
                        }
                      else{
                          response.sendRedirect("noPassMatch.jsp");
                      }
                    }
                    
             }catch(SQLException e){
                    e.printStackTrace();
                }           
    }
    
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

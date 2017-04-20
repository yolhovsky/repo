package mypack;


import java.io.IOException;
import java.io.PrintWriter;
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

public class FirstExisting extends HttpServlet {
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

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = new String();
        HttpSession session = request.getSession(); 
        name = session.getAttribute("user1").toString();    
        String[] skillsowned = new String[5];
        String[] skillswanted = new String[5];
        String zip = null;
        String email = null;
        //out.println("name: " + name); // working
  
        PreparedStatement checkUser = null;
        ResultSet rs = null;
        try {
            checkUser = con.prepareStatement("SELECT username, skillsowned, skillswanted, zip, email from flyingcars.fullinfo WHERE username = ?");
            checkUser.setString(1, name);
            rs = checkUser.executeQuery();
    
            int i = 0;
        while(rs.next()){  
            if(i==0){
            session.setAttribute("user1", name);//name
            email = rs.getString("email");
            zip = rs.getString("zip");
            skillsowned[i] = rs.getString("skillsowned");
            skillswanted[i] = rs.getString("skillswanted");
            session.setAttribute("email", email);
            session.setAttribute("zip", zip);
            session.setAttribute("ownedSkill1", skillsowned[0]);
            session.setAttribute("wantedSkill1", skillswanted[0]);
            
            request.getParameter("ownedSkill1");
            request.setAttribute("ownedSkill1", skillsowned[0]);
            request.setAttribute("wantedSkill1", skillsowned[0]);
            
            i++;
        }
            else{ 
            skillsowned[i] = rs.getString("skillsowned");
            skillswanted[i] = rs.getString("skillswanted");
            session.setAttribute("ownedSkill2", skillsowned[1]);
            session.setAttribute("wantedSkill2", skillswanted[1]);
            session.setAttribute("ownedSkill3", skillsowned[2]);
            session.setAttribute("wantedSkill3", skillswanted[2]);
            session.setAttribute("ownedSkill4", skillsowned[3]);
            session.setAttribute("wantedSkill4", skillswanted[3]);
            session.setAttribute("ownedSkill5", skillsowned[4]);
            session.setAttribute("wantedSkill5", skillswanted[4]);
            i++;   
                }           
        }
        }catch (SQLException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("showInfo.jsp");
       
    }   
}


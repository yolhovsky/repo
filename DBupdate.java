package mypack;



import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
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


public class DBupdate extends HttpServlet {
    Connection con = null;
    
    PreparedStatement updateUser = null;
    //PreparedStatement UserRowNum = null;
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
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            
            throws ServletException, IOException {
        response.setContentType("text/html");
            //PrintWriter out = response.getWriter();
            int result = 0; //returns how many rows got affected
             try{ 
                    String name = new String();
                    String wantedSkill1 = new String();
                    String wantedSkill2 = new String();
                    String wantedSkill3 = new String();
                    String wantedSkill4 = new String();
                    String wantedSkill5 = new String();
                    String ownedSkill1 = new String();
                    String ownedSkill2 = new String();
                    String ownedSkill3 = new String();
                    String ownedSkill4 = new String();
                    String ownedSkill5 = new String();                    
                    String email = new String();
                    int zip = 0;
                    
                    HttpSession session = request.getSession(); 
                    
//                    UserRowNum = con.prepareStatement("with t1 as (select distinct rownum from flyingcars.fullinfo \n" +
//"where username = ?)\n" +
//"select rownum from t1 where rownum > (select rownum from t1 order by rownum asc limit 1)  \n" +
//"limit 1");
                    
                    
                    
                    
                    updateUser = con.prepareStatement("update flyingcars.fullinfo \n" +
"SET skillswanted = ?, skillsowned = ?\n" +
"WHERE rownum = \n" +
"(with t1 as (select distinct rownum from flyingcars.fullinfo \n" +
"where username = ? order by rownum)\n" +
"select rownum from t1 where rownum >=  (select rownum from t1 order by rownum asc limit 1)+ ? \n" +
"limit 1)");
                    //insertUser = con.prepareStatement( "INSERT INTO flyingcars.fullinfo (username, skillsowned, skillswanted, zip, email) VALUES (?,?,?,?,?)");
                    name = session.getAttribute("user1").toString();
                    email = session.getAttribute("email").toString();
                    zip = Integer.parseInt(session.getAttribute("zip").toString());
                   // name = request.getParameter("user1");
                   // email = request.getParameter("email");
                    
                    wantedSkill1 = request.getParameter("wantedSkill1");
                    ownedSkill1 = request.getParameter("ownedSkill1");
                    updateUser.setString(1, wantedSkill1);
                    updateUser.setString(2, ownedSkill1);
                    updateUser.setString(3, name);
                    updateUser.setInt(4, 0);
                    updateUser.executeUpdate();
                    
                    //UserRowNum.setString(1, name);
                    //rs = UserRowNum.executeQuery();
                    
                    
                    
                    wantedSkill2 = request.getParameter("wantedSkill2");
                    ownedSkill2 = request.getParameter("ownedSkill2");
                    updateUser.setString(1, wantedSkill2);
                    updateUser.setString(2, ownedSkill2);
                    updateUser.setString(3, name);
                    //updateUser.setInt(4, 14);
                    
                    updateUser.setInt(4, 1);
                    updateUser.executeUpdate();
                    
                    wantedSkill3 = request.getParameter("wantedSkill3");
                    ownedSkill3 = request.getParameter("ownedSkill3");
                    updateUser.setString(1, wantedSkill3);
                    updateUser.setString(2, ownedSkill3);
                    updateUser.setString(3, name);
                    //updateUser.setInt(4, 15);
                    updateUser.setInt(4, 2);
                    //updateUser.setInt(4, parseInt("(select rownum from t1 order by rownum asc limit 1) + 1 "));
                    updateUser.executeUpdate();
                    
                    wantedSkill4 = request.getParameter("wantedSkill4");
                    ownedSkill4 = request.getParameter("ownedSkill4");
                    updateUser.setString(1, wantedSkill4);
                    updateUser.setString(2, ownedSkill4);
                    updateUser.setString(3, name);
                    //updateUser.setInt(4, 16);
                    updateUser.setInt(4, 3);
                    updateUser.executeUpdate();
                    
                    wantedSkill5 = request.getParameter("wantedSkill5");
                    ownedSkill5 = request.getParameter("ownedSkill5");
                    updateUser.setString(1, wantedSkill5);
                    updateUser.setString(2, ownedSkill5);
                    updateUser.setString(3, name);
                    //updateUser.setInt(4, 17);
                    updateUser.setInt(4, 4);
                    updateUser.executeUpdate();
                    
                    
                    
                    
                    String[] wantedSkillsArray = {wantedSkill1,wantedSkill2,wantedSkill3,wantedSkill4,wantedSkill5};
                    String[] ownedSkillsArray = {ownedSkill1,ownedSkill2,ownedSkill3,ownedSkill4,ownedSkill5};
                    
//                    int i = 0;
//                    
//                    while(i<5){
//                    updateUser.setString(1, name);
//                    updateUser.setString(2, wantedSkillsArray[i]);
//                    updateUser.setString(3, ownedSkillsArray[i]);
//                    updateUser.setInt(4, zip);
//                    updateUser.setString(5, email);         
//                    updateUser.executeUpdate();
//                    i++;
//                    }
                    
                    session.setAttribute("user1", name); 
                    session.setAttribute("zip", zip); 
                    session.setAttribute("email", email); 
                    session.setAttribute("wantedSkill1", wantedSkill1);
                    session.setAttribute("ownedSkill1", ownedSkill1);
                    session.setAttribute("wantedSkill2", wantedSkill2);
                    session.setAttribute("ownedSkill2", ownedSkill2);
                    session.setAttribute("wantedSkill3", wantedSkill3);
                    session.setAttribute("ownedSkill3", ownedSkill3);
                    session.setAttribute("wantedSkill4", wantedSkill4);
                    session.setAttribute("ownedSkill4", ownedSkill4);
                    session.setAttribute("wantedSkill5", wantedSkill5);
                    session.setAttribute("ownedSkill5", ownedSkill5);
                    response.sendRedirect("showInfo.jsp");
                    
                    
             }catch(SQLException e){
                    e.printStackTrace();
                }
            }
    }


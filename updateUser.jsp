<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.sql.*"%>
<% Class.forName("org.postgresql.Driver"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> User Info </title>
    </head>
    <body>
    <%!
        public class UserInfo { 
          
        String URL = "jdbc:postgresql://localhost:5432/midterm";
        String USERNAME = "postgres";
        String PASSWORD = "<password>";
        Connection con = null;
        PreparedStatement selectSkillowned = null;
        PreparedStatement selectSkillwanted = null;
        PreparedStatement insertInfo = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        


        public ResultSet selectSkills(){
                try{
                    con = DriverManager.getConnection(URL,USERNAME,PASSWORD);  
                    selectSkillowned = con.prepareStatement( "SELECT skillname FROM flyingcars.skills ");
                    selectSkillwanted = con.prepareStatement( "SELECT skillwanted FROM flyingcars.fullinfo ");
                    rs =  selectSkillowned.executeQuery();
                    rs1 =  selectSkillwanted.executeQuery();
             
                }catch(SQLException e){
                    e.printStackTrace();
                }
              
              return rs;
            }  
}
%>  <form name="Edit" action="DBupdate" method="POST">   
         <table border="1">
                
                <tbody>
                 <h1>Please fill in info:</h1>
                    <tr>
                        <td>Username :</td>
                        <td><%= session.getAttribute("user1")   %> </td>
                        
                    </tr>
                    
                    <tr>
                        <td> Email : </td>
                        <td> <%= session.getAttribute("email")%> </td>
                    </tr>
                    <tr>
                        <td> Zip : </td>
                        <td> <%= session.getAttribute("zip")%> </td><br>
                    </tr>
                    
                   <table border="1">
                        
                        <tbody>
                             <h1>Please choose skills you own: </h1>
                             <% UserInfo skills = new UserInfo();  
                                ResultSet skillset = null;
                             %>
                                
                            
                            <tr>
                                <td>Skill 1: </td>
                                <td> <select name="ownedSkill1">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("ownedSkill1")%></option>
                                        <% while(skillset.next()) { %>                     
                                        <option> <%= skillset.getString("skillName")%></option>
                                        <% } %>
                                    </select> </td>
                            </tr>
                            <tr>
                                <td>Skill 2: </td>
                                <td> <select name="ownedSkill2">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("ownedSkill2")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td>                                  
                            </tr>
                            <tr>
                                <td>Skill 3: </td>
                                <td> <select name="ownedSkill3">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("ownedSkill3")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td> 
                            </tr>
                            <tr>
                                <td>Skill 4: </td>
                                <td> <select name="ownedSkill4">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("ownedSkill4")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td> 
                            </tr>
                            <tr>
                                <td>Skill 5: </td>
                                <td> <select name="ownedSkill5">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("ownedSkill5")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td> 
                            </tr>
                        </tbody>
                    </table>
                    
                    
                    
                     <table border="1">
                        
                        <tbody>
                             <h1>Please enter skills you want to acquire: </h1>
                            <tr>
                                <td>Skill 1: </td>
                                <td> <select name="wantedSkill1">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("wantedSkill1")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td> 
                            </tr>
                            <tr>
                                <td>Skill 2: </td>
                                <td> <select name="wantedSkill2">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("wantedSkill2")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td>
                            </tr>
                            <tr>
                                <td>Skill 3: </td>
                                <td> <select name="wantedSkill3">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("wantedSkill3")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td>
                            </tr>
                            <tr>
                                <td>Skill 4: </td>
                                <td> <select name="wantedSkill4">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("wantedSkill4")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td>
                            </tr>
                            <tr>
                                <td>Skill 5: </td>
                                <td> <select name="wantedSkill5">
                                        <% skillset = skills.selectSkills(); %>
                                        <option selected> <%= session.getAttribute("wantedSkill5")%></option>
                                        <% while(skillset.next()) { %>
                                        <option> <%= skillset.getString("skillName") %></option> 
                                        <% } %>
                                    </select> </td>
                            </tr>
                            
                        </tbody>
                    </table>             
             <input type="submit" value="Submit info" name="submitinfo" /> 
        
                 
                    </form>
                
    </body>
</html>
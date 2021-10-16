/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rupak
 */
public class deletelib extends HttpServlet {

   public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String nm1=req.getParameter("n1");
        
    
       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                  
                  PreparedStatement ps=con.prepareStatement("select * from t13 where LID=?");
      ps.setString(1,nm1);
      ResultSet rs=ps.executeQuery();



RequestDispatcher rd=req.getRequestDispatcher("test3.html");
                   rd.include(req,res);


        if(rs.next())
        {
               String name=rs.getString(1);
               String lid=rs.getString(2);
          PreparedStatement ps1=con.prepareStatement("delete from t13 where LID=?");
           ps1.setString(1,lid);
          int i=ps1.executeUpdate();
          
             
             out.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Thanku Admin you deleted "+name+"  succesfully  \n" +
"</center></body></html>");   
               
        
        }
       else
       {
          
          
    out.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry please enter valid librarian id  \n" +
"</center></body></html>");    
    }
   
       
        con.close();
        
            } 
            catch (Exception ex) {
               out.println(ex);
            }
	
    }
}

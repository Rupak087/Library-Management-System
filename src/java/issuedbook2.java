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
public class issuedbook2 extends HttpServlet {

   public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String nm1=req.getParameter("n1");
        
    
       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
 
                   PreparedStatement ps1=con.prepareStatement("select * from t11 where SID=?");
                   ps1.setString(1,nm1);
                   ResultSet rs=ps1.executeQuery();                  

                 
RequestDispatcher rd=req.getRequestDispatcher("test2.html");
                   rd.include(req,res);

   if(rs.next())
   {

        PreparedStatement ps=con.prepareStatement("select * from t14 where SID=?");
                  ps.setString(1,nm1);
                  rs=ps.executeQuery();

         out.println("<table border=1 width=100% height=30%>");
      out.println("<tr><th><b>STUDENT ID</th><th><b>BOOK ID</th><th><b>QUANTITY</th><th><b>ISSUED DATE/TIME</th></tr>");
      while(rs.next())
      {
         
         out.println("<tr><td><center>"+rs.getString(1)+"</center>"+"</td><td><center>"+rs.getString(2)+"</center>"+"</td><td><center>"+rs.getString(3)+"</center>"+"</td><td><center>"+rs.getString(4)+"</center>"+"</td></tr>");
      }
     out.println("</table>");
     out.println("</html></body>");
   }
   else
   {
         out.println("<br><center><h2>Please enter valid student Id</center>");
   }    

       
        con.close();
        
            } 
            catch (Exception ex) {
               out.println(ex);
            }
	
    }
}

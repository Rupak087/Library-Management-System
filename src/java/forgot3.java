/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rupak
 */
public class forgot3 extends HttpServlet 
{
    
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String nm1=req.getParameter("n3");
                
        
    
       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
                   HttpSession ses=req.getSession();
                     String id =(String)ses.getAttribute("R");

                  Statement stmt=con.createStatement();
                  String q1="update t11 set pwd='"+nm1+"'where GMAIL='"+id+"'";
                  
                  ResultSet rs=stmt.executeQuery(q1);
        if(rs.next())
        {
           pw.println("<html><body bgcolor=hotpink> <br><br><br><br><br><br><br><br><br><br><br><br>"
                        + "<center><font face=Matura MT Script Capitals size=7><u><i>Your <strong>Password</strong> is Updated successfully"
                        + "</i></u></font></center><br> <br><center>For Sign IN:<a href=\"signin.html\"><strong>Click Here</strong></a><br><br>"
                   + "For Home:<a href=\"home1.html\"><strong>Click Here</strong></a></center></body></html>");
        }
       
        con.close();
        
            } 
            catch (Exception ex) {
               pw.println(ex);
            }
	
    }

    
    
}

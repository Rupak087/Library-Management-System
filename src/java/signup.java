/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rupak
 */
public class signup extends HttpServlet 
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pwl=res.getWriter();
        String nm1=req.getParameter("n1");
        String nm2=req.getParameter("n2");
        String nm3=req.getParameter("n3");
        String nm4=req.getParameter("n4");
        String nm5=req.getParameter("n5");
        String nm6=req.getParameter("n6");

       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                  
                  Statement stmt=con.createStatement();
                  String q1="insert into t11 values('"+nm1+"','"+nm2+"','"+nm3+"','"+nm4+"','"+nm5+"','"+nm6+"')";
                  
                  int x=stmt.executeUpdate(q1);
                  if(x>0)
                  {
                               pwl.println("<script language='javascript'>alert('Registration success');</script>");
                               RequestDispatcher rd=req.getRequestDispatcher("home1.html");
                               rd.include(req,res);
                     
                  } 
        }
            catch (Exception ex) {
               pwl.println(ex);
            }
	
        
    }
}

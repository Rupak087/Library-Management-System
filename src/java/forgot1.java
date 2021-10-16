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
public class forgot1 extends HttpServlet 
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String nm=req.getParameter("n2");
       
     
    
       
        try {
                
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
                    HttpSession ses=req.getSession();
                    ses.setAttribute("R",nm);
                    Statement stmt=con.createStatement();
                    String q1="select* from t11 where GMAIL='"+nm+"'";
                    ResultSet rs=stmt.executeQuery(q1);
                     if(rs.next())
                    {
                        pw.println("<html>\n" +
                        "    <head>\n" +
                        "       \n" +
                        "    </head>\n" +
                        "    <body  background=\"lib1.jpg\">\n" +
                        "         <br><br><br>\n" +
                        "    <center><h1><strong><u>Answer Page</u></strong></h1></center>\n" +
                        "        <br><br><br><br><form method=\"post\" action=\"forgot2 \">\n" +
                        "<table border=\"0\" width=\"30%\"align=\"center\"> \n" +
                        "\n" +
                        "<td>security Question:</td>\n" +
                        "<td align=\"right\"><input type=\"text\" name=\"n4\" value="+rs.getString(5)+"></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td>answer:</td>\n" +
                        "<td align=\"right\"><input type=\"text\" name=\"n5\" ></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td><input type=\"submit\" name=\"n6\" value=\"submit\"></td>\n" +
                        "<td align=\"right\"><input type=\"reset\" name=\"n6\" value=\"refresh\"></td>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "</form>\n" +
                        "    </body>\n" +
                        "</html>");
                    }
                    else
                     {
                         pw.println("<html><body bgcolor=red> <br><br><br><br><br><br><br><br><br><br><br><br>"
                        + "<center><font face=Matura MT Script Capitals size=7><u><i>Your <strong>Gmail</strong> is Wrong "
                        + "</i></u></font></center><br> <br><center>For Sign IN Other Account:<a href=\"home1.html\"><strong>Click Here</strong></a><br><br>"
                   + "For Sign up:<a href=\"signup.html\"><strong>Click Here</strong></a><br>TO Go Back:<a href=\"forgot.html\"><strong>Click Here</strong></a></center></body></html>");
                     }
       
                    con.close();
        
            } 
            catch (Exception ex) 
            {
                pw.println(ex);
            }
	
        
    }
}
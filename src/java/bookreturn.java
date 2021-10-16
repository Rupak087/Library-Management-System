/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rupak
 */
public class bookreturn extends HttpServlet 
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pwl=res.getWriter();
        String BID=req.getParameter("n1");
try
{
        Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
      
         PreparedStatement ps0=con.prepareStatement("select * from t14 where BID=?");//first for checking the id is valid or not
                    ps0.setString(1,BID);
                    ResultSet rs=ps0.executeQuery();
                     if(rs.next())
                     {
     
      

 Cookie ck[]=req.getCookies();
         
  if(ck!=null)
  {      
       
                   String name=ck[0].getValue();
                  if(!name.equals("")||name!=null)
                 {
                    PreparedStatement ps=con.prepareStatement("select * from t11 where name=?");
                    ps.setString(1,name);
                    rs=ps.executeQuery();
                     if(rs.next())
                     {
         
                        String SID=rs.getString(4);
                        PreparedStatement ps2=con.prepareStatement("select * from t14 where SID=?");
                        ps2.setString(1,SID);
                        ResultSet rs1=ps2.executeQuery();
                        int c=0;
                        while(rs1.next())
                        {
                             c++;
                        }
                        if(c==0)
                        {
                          
                          RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
              
                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Currently you Don't have any book  \n" +
"</center></body></html>");



 
                        }
                        else
                        {
                        PreparedStatement ps1=con.prepareStatement("delete from t14 where BID=?");
                        ps1.setString(1,BID);
                        
                        int x=ps1.executeUpdate(); 
                         if(x>0)
                        {
                          
                                      Statement stmt4=con.createStatement();
                          String q4="select * from t12 where BID='"+BID+"'";
                          rs=stmt4.executeQuery(q4);
                         if(rs.next())
                         {
                                int n=rs.getInt(4);
                          
                                int p=n+1;
                                
                               PreparedStatement ps3=con.prepareStatement("UPDATE t12 SET QUANTITY=? where BID=?");
                                ps3.setInt(1,p);
                               ps3.setString(2,BID);
                              int k=ps3.executeUpdate();
                          
                         }







                          RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
                          


                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Thanks "+name+" you have submitted the book  \n" +
"</center></body></html>");


                        } 
                         
                       }
                     }
                  
                 }


 
                  
           
    }
     else
  {
   pwl.print("please Login first");
     req.getRequestDispatcher("signin.html").include(req,res);
  }
}
else
{
  RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
              
                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry this book is not issued by you or invalid book Id  \n" +
"</center></body></html>");

}
 }
            catch (Exception ex)
           {
               pwl.println(ex);
            }
	
        
    }
}

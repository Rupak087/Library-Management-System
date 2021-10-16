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


public class bookissue extends HttpServlet 
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pwl=res.getWriter();
        String nm1=req.getParameter("n1");
try
{
       Class.forName("oracle.jdbc.driver.OracleDriver");
       Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

       PreparedStatement ps0=con.prepareStatement("select * from t12 where BID=?");//first for checking the id is valid or not
       ps0.setString(1,nm1);
        ResultSet rs=ps0.executeQuery();
        if(rs.next())
       { 
             int n1=rs.getInt(4);
             if(n1>0)
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
                        if(c>=2)
                        {
                          
                          RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
              
                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>You have already taken 2 Book  \n" +
"</center></body></html>");



 
                        }
                        else
                        {

                           Statement stmt=con.createStatement();
                          String q1="select * from t14 where BID='"+nm1+"' and SID='"+SID+"'";
                          rs=stmt.executeQuery(q1);
                         if(rs.next())
                         {
                            RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
              
                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry you have already taken this Book  \n" +
"</center></body></html>");
    
                         }
                        else
                        {

                             java.util.Date date=new java.util.Date();
			
			
			java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			
                        
                        PreparedStatement ps1=con.prepareStatement("insert into t14 values(?,?,?,?)");
                        ps1.setString(1,SID);
                        
                        ps1.setString(2,nm1);
                        ps1.setInt(3,1);
           
			ps1.setTimestamp(4,sqlTime);
                        int x=ps1.executeUpdate(); 
                         if(x>0)
                        {
                          
                           Statement stmt4=con.createStatement();
                          String q4="select * from t12 where BID='"+nm1+"'";
                          rs=stmt4.executeQuery(q4);
                         if(rs.next())
                         {
                                int n=rs.getInt(4);
                                
                                int p=n-1;
                               PreparedStatement ps3=con.prepareStatement("UPDATE t12 SET QUANTITY=? where BID=?");
                                ps3.setInt(1,p);
                               ps3.setString(2,nm1);
                              int k=ps3.executeUpdate();
                          
                         }

                          RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
                           


                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Thanks "+name+" for Issueing the book  \n" +
"</center></body></html>");


                        } 
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
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry currently This book is not available  \n" +
"</center></body></html>");
  
}
}
else
{
  RequestDispatcher rd=req.getRequestDispatcher("user.html");
                          rd.include(req,res);
              
                         pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry invalid book Id  \n" +
"</center></body></html>");
  
}

}
            catch (Exception ex)
           {
               pwl.println(ex);
            }
	
        
    }
}
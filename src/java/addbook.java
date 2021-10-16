import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addbook extends HttpServlet 
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

       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                  
                  Statement stmt=con.createStatement();
                  String q1="insert into t12 values('"+nm1+"','"+nm2+"','"+nm3+"','"+nm4+"','"+nm5+"')";
                  
                  int x=stmt.executeUpdate(q1);
                  if(x>0)
                  {
                     pwl.println("Thanku for Updateing"); 
                     RequestDispatcher rd=req.getRequestDispatcher("addbook.html");
                     rd.include(req,res);
                  } 
        }
            catch (Exception ex) {
               pwl.println(ex);
            }
	
        
    }
}

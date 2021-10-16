import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addbook2 extends HttpServlet 
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pwl=res.getWriter();
        String nm1=req.getParameter("n1");
        String nm2=req.getParameter("n2");
        int n=Integer.parseInt(nm2);
              
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                 PreparedStatement ps0=con.prepareStatement("select * from t12 where BID=?");//first for checking the id is valid or not
                 ps0.setString(1,nm1);
                 ResultSet rs=ps0.executeQuery();
               if(rs.next())
              { 
                  int n1=n+rs.getInt(4);
                  PreparedStatement ps3=con.prepareStatement("UPDATE t12 SET QUANTITY=? where BID=?");
                  ps3.setInt(1,n1);
                  ps3.setString(2,nm1);
                  int x=ps3.executeUpdate();
                  if(x>0)
                  {
                     pwl.println("Thanku for Updateing"); 
                     RequestDispatcher rd=req.getRequestDispatcher("addbook.html");
                     rd.include(req,res);
                  } 
            }
            else
           {
             pwl.println("Sorry this book is not available");    
             RequestDispatcher rd=req.getRequestDispatcher("addbook.html");
                     rd.include(req,res);
             
             
           }
        }
            catch (Exception ex) {
               pwl.println(ex);
            }
	 }
      }

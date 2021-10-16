
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author rupak
 */
public class issuedbook extends HttpServlet
{
  protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
  {
    res.setContentType("text/html");
    PrintWriter out=res.getWriter();
  //req.getRequestDispatcher("link.html").include(req,res);
  Cookie ck[]=req.getCookies();
  if(ck!=null)
  {
    try
    {
         Class.forName("oracle.jdbc.OracleDriver");
         Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
        String name=ck[0].getValue();
    if(!name.equals("")||name!=null)
    {
      PreparedStatement ps=con.prepareStatement("select * from t11 where name=?");
      ps.setString(1,name);
      ResultSet rs=ps.executeQuery();
      
     

RequestDispatcher rd=req.getRequestDispatcher("test.html");
                   rd.include(req,res);

     if(rs.next())
     {
      String SID=rs.getString(4);
       PreparedStatement ps1=con.prepareStatement("select  * from t14 where SID=?");
      ps1.setString(1,SID);
       rs=ps1.executeQuery();
    
out.println("<table border=1 width=100% height=30%>");
    out.println("<tr><th><b>STUDENT ID</th><th><b>BOOK ID</th><th><b>QUANTITY</th><th><b>ISSUED DATE/TIME</th></tr>");
      while(rs.next())
      {
         
         out.println("<tr><td><center>"+rs.getString(1)+"</center>"+"</td><td><center>"+rs.getString(2)+"</center>"+"</td><td><center>"+rs.getString(3)+"</center>"+"</td><td><center>"+rs.getString(4)+"</center>"+"</td></tr>");
      }
     out.println("</table>");
     out.println("</html></body>");
    
   }





















   }
  }
    catch(Exception e)
  {
     e.printStackTrace();
   }
 }
 else
{
  out.print("please Login first");
req.getRequestDispatcher("signin.html").include(req,res);
}
out.close();
}
}

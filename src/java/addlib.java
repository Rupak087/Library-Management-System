import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addlib extends HttpServlet 
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pwl=res.getWriter();
        String nm1=req.getParameter("n1");
        String nm2=req.getParameter("n2");
        String nm3=req.getParameter("n3");
        String nm4=req.getParameter("n4");

       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                  
                  Statement stmt=con.createStatement();
                  String q1="insert into t13 values('"+nm1+"','"+nm2+"','"+nm3+"','"+nm4+"')";
                  
                  int x=stmt.executeUpdate(q1);
 
RequestDispatcher rd=req.getRequestDispatcher("test3.html");
                   rd.include(req,res);
                  if(x>0)
                  {
                       pwl.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Thanku Admin you have Succesfully Add "+nm1+"  \n" +
"</center></body></html>");   
                 } 
          }
            catch (Exception ex) {
               pwl.println(ex);
            }
	
        
    }
}

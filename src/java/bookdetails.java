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


public class bookdetails extends HttpServlet {

   public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String nm1=req.getParameter("n1");
        int n=Integer.parseInt(nm1);
        if(n>=1&&n<=8)
        {
    
       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                  
                  PreparedStatement ps=con.prepareStatement("select * from t12 where SEM=?");
                  ps.setString(1,nm1);
                 ResultSet rs=ps.executeQuery();


RequestDispatcher rd=req.getRequestDispatcher("test.html");
                   rd.include(req,res);

out.println("<table border=1 width=100% height=30%>");
      out.println("<tr><th><b>BID</th><th><b>NAME</th><th><b>AUTHOR</th><th><b>QUANTITY</th><th><b>SEM</th></tr>");

        while(rs.next())
        {  
                    
 
            out.println("<tr><td><center>"+rs.getString(1)+"</center>"+"</td><td><center>"+rs.getString(2)+"</center>"+"</td><td><center>"+rs.getString(3)+"</center>"+"</td><td><center>"+rs.getInt(4)+"</center>"+"</td><td><center>"+rs.getInt(5)+"</center>"+"</td></tr>");
            
        }
   out.println("</table>");
     out.println("</html></body>");
    

       
        con.close();
        
            } 
            catch (Exception ex) {
               out.println(ex);
            }
     }
     else
    {
                 RequestDispatcher rd=req.getRequestDispatcher("test.html");
                   rd.include(req,res);
                out.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry please enter valid semester[1-8] \n" +
"</center></body></html>");
                   
    }
	
    }
}


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class logoutlib extends HttpServlet
{
  protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
  {
    res.setContentType("text/html");
    PrintWriter out=res.getWriter();
  req.getRequestDispatcher("logout.html").include(req,res);
  Cookie ck=new Cookie("name","");
  ck.setMaxAge(0);
  res.addCookie(ck);
  
}
}
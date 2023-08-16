package logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entites.*;;
/**
 * Servlet implementation class Logincheck
 */
@WebServlet("/logincheck")
public class Logincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String uid=request.getParameter("uid");
		String pass=request.getParameter("pass");
		try
		{
		ps=con.prepareStatement("Select * from users where u_id=? and password=?");
		ps.setString(1,uid);
		ps.setString(2,pass);
		rs=ps.executeQuery();
		 if(rs.next())
		 {
			 Cookie [] allc=request.getCookies();
			 if(allc!=null)
			 {
				 for(Cookie C :allc)
				 {
					 if(C.getName().equals("loginerror"))
					 {
						 C.setMaxAge(0);
						 response.addCookie(C);
					 }
				 }
			 }
			 User user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			 HttpSession session=request.getSession();
			 session.setAttribute("loggedinuser",user);
			 RequestDispatcher rs=request.getRequestDispatcher("/homeservlet");
			 rs.forward(request, response);
		 }
		 else 
		 {
			 Cookie allc=new Cookie("loginerror","Wrong_UID/PWD");
			 response.addCookie(allc);
			 response.sendRedirect("http://localhost:8080/Shoppingapp/login.jsp");
		 }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				rs.close();
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
		
	}

}

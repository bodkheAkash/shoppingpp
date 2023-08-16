package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entites.*;

/**
 * Servlet implementation class Confirmcart
 */
@WebServlet("/confirmcart")
public class Confirmcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
       PreparedStatement ps;
    
   
	public void init(ServletConfig config) throws ServletException 
	{
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 response.setContentType("text/html");
		int n;
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		User u=(User) session.getAttribute("loggedinuser");
		String name=u.getFname();
		java.sql.Timestamp ts = new java.sql.Timestamp(new java.util.Date().getTime());
		
		float price=(float)session.getAttribute("Tprice");
		 
		try
		{
			ps=con.prepareStatement("insert into shopping(user_id,shoppingDate,totalprice) values(?,?,?)");
			ps.setString(1, name);
			ps.setTimestamp(2, ts);
			ps.setFloat(3, price);
			n=ps.executeUpdate();
			if(n==1)
			{
				out.print("<h3>Thank You for shooping "+u.getFname()+" "+u.getMname()+" "+u.getLname()+"</h3>");
				out.print("<p> Order is confirmed </p>");
				out.print("<p> You bill will be emailed at "+ u.getEmail()+"</p>");
				out.print("<p> You will receive message on "+ u.getContact() +" before order delivery </p>");
				
				out.print("<br/> <br/> <a href='logout'> Logout <a/>");
				out.print("<br/> <br/> <a href='homeservlet'> Want to set new order? <a/>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		doGet(request, response);
	}

}

package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Servlet implementation class Viewcart
 */
@WebServlet("/viewcart")
public class Viewcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		List<Integer> sps=(List<Integer>)session.getAttribute("cart");
		if(sps==null)
		{
			out.print("<p>No product are Selected</p>");
		}
		else
		{
			float price = 0;
			try
			{
				int no=0;
				
				out.print("<h1>Products are in cart's</h1>");
				
				ps=con.prepareStatement("select * from product where p_id=?");
				out.print("<table border='1' style='border-collapse:collapse'>");
				for(int n :sps)
				{
					ps.setInt(1, n);
					rs=ps.executeQuery();
					if(rs.next())
					{
						out.print("<tr>");
						out.print("<td>"+(++no)+"</td>");
						out.print("<td>"+rs.getString(2)+"</td>");
						out.print("<td>"+rs.getFloat(4)+"</td>");
						out.print("</tr>");
						price+=rs.getFloat(4);
					}
				}
				out.print("<tr>");
					out.print("<td colspan='2'> Total price </td>");
					out.print("<td>"+price+"</td>");
					session.setAttribute("Tprice",price);
			out.print("<tr>");
			out.print("</table>");
				out.print("<br/> <br/> <a href='confirmcart'> Confirm Cart <a/>");
				out.print("<br/> <br/> <a href='homeservlet'> Go back to Categories <a/>");
				
				
			}
			catch(Exception e)
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
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}


	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

}

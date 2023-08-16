package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Homeservlet
 */
@WebServlet("/homeservlet")
public class Homeservlet extends HttpServlet {
	Connection con;
	ResultSet rs;
	Statement st;
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		RequestDispatcher rd=request.getRequestDispatcher("/header");
		rd.include(request, response);
		PrintWriter out=response.getWriter();
		try
		{
			out.print("<h1>List of Category<hr></hr></h1>");
			st=con.createStatement();
			rs=st.executeQuery("Select * from category");
			while(rs.next())
			{
				out.println("&nbsp<a href='getProdcuts?cid="+rs.getInt(1)+"'>"+rs.getString(2)+"</a>");   
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
				st.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		
		
	}

}

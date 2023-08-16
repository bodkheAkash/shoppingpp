package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Productes
 */
@WebServlet("/getProdcuts")
public class Productes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   Connection con;
   ResultSet rs;
   PreparedStatement ps;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		int cat_id=Integer.parseInt(request.getParameter("cid"));
		try
		{
			response.setContentType("text/html");
			ps=con.prepareStatement("select * from product where cat_id=?");
			ps.setInt(1, cat_id);
			rs=ps.executeQuery();
			out.print("<form action='http://localhost:8080/Shoppingapp/addtocart' method='get'>");
			out.print("Select product :");
			out.print("<select name=selectedP>");
			while(rs.next())
			{
				System.out.println("hii");
				System.out.println(rs.getString(2));
				out.print("<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>");
				
			}
			out.print("</select>");
			out.print("<br/><input type='submit' value='Add to cart'/>");
			//out.print("<input type='hidden' name='productname' value='"+rs.getString(2)+"'/>");
			out.print("</form>");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				rs.close();
				ps.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
		

	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
	
		super.init(config);
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

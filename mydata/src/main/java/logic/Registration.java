package logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registration")
public class Registration extends HttpServlet 
{
	Connection con;
	PreparedStatement ps;
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uid=request.getParameter("uid");
		String pass=request.getParameter("pass");
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String contact=request.getParameter("no");
		System.out.println(uid);
		try
		{
		  ps=con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
		    ps.setString(1,uid);
		    ps.setString(2, pass);
		    ps.setString(3, fname);
		    ps.setString(4,mname);
		    ps.setString(5, lname);
		    ps.setString(6, email);
		    ps.setString(7, contact);
		    int n=ps.executeUpdate();
		    
		    if(n==1)
		    {
		    	response.sendRedirect("http://localhost:8080/Shoppingapp/login.jsp");
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

}

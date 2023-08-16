package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


@WebServlet("/addtocart")
public class Addtocart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		int n=Integer.parseInt(request.getParameter("selectedP"));
		//String name=request.getParameter("productname");
		//System.out.println(name);
		PrintWriter out=response.getWriter();
		HttpSession session1=request.getSession();
		//HttpSession session2=request.getSession();
		List<Integer> product=(List<Integer>)session1.getAttribute("cart");
		//List<String>productname=(List<String>)session2.getAttribute("cartPN");
		//System.out.println(" id"+request.getParameter("selectedP"));
		if(product==null )//&& productname==null
		{
			product =new ArrayList<>();
			//productname=new ArrayList<>();
		}
		product.add(n);
		//productname.add(name);
		session1.setAttribute("cart",product);
		//session2.setAttribute("cartPN",productname);
		
		out.print("<h3>Product added in the cart</h3>");
		//out.print("<br/>Product name "+name);
		out.print("<br/><h5>There are "+product.size()+"Item(s) in the cart</h5>");
		
//		out.print("<br/><a href='viewcart'>View cart</a>");
		out.print("<br/><a href='ViewCart.jsp'>View cart</a>");
		out.print("<br/><a href='homeservlet'>Category</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

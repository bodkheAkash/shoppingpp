package assignment_3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Two
 */
@WebServlet("/two")
public class Two extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int n=0;
		PrintWriter out=response.getWriter();
		Cookie [] allcookies=request.getCookies();
		if(allcookies!=null)
		{
			for(Cookie c :allcookies)
			{
				if(c.getName().equals("visitcount"))
					n=Integer.parseInt(c.getValue());
			}
		}
		n++;
		Cookie c=new Cookie("visitcount", ""+n);
		response.addCookie(c);
		out.print("Visit Count "+n);
		out.print("<br/><a href='http://localhost:8080/Shoppingapp/two'>Refresh</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

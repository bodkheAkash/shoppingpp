package listener;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener 
{
    Connection con;
   
    public Listener() 
    {
        
    }

	
    public void contextDestroyed(ServletContextEvent sce)  
    { 
         try
         {
        	 con.close();
         }
         catch (Exception e) 
        {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent sce)  
    { 
    	String driver=sce.getServletContext().getInitParameter("driverclass");
    	String url=sce.getServletContext().getInitParameter("jdbcurl");
    	String user=sce.getServletContext().getInitParameter("user");
    	String pass=sce.getServletContext().getInitParameter("password");
        try
        {
        	Class.forName(driver);
        	con=DriverManager.getConnection(url,user,pass);
        	sce.getServletContext().setAttribute("jdbccon",con);
        	System.out.println("Connection is Created");
        }
        catch (Exception e) 
        {
			e.printStackTrace();
		}
    }
	
}

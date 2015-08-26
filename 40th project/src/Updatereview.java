

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
/**
 * Servlet implementation class helloworld
 */



@WebServlet("/Updatereview")
public class Updatereview extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
			HttpSession session = request.getSession();
			
			String curuser = (String) session.getAttribute("curuser");
			
			String rests = "";
			
				
		  try
			{
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
				
		    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

		    	
				//properties for creating connection to Oracle database
				Properties props = new Properties();
				props.setProperty("user", "testdb");
				props.setProperty("password", "password");
			   
		    	
		    	Connection conn = DriverManager.getConnection(url,props);

		    	
		    	String sql = "select rests.rname, rests.rid from rests inner join reviews on rests.rid = reviews.rest_id where reviews.user_id = ?";
				
				PreparedStatement preStatement = conn.prepareStatement(sql);
				preStatement.setString(1,curuser);
				
				
				ResultSet result;
				result = preStatement.executeQuery();
				
				while(result.next())
				{
					int restid = result.getInt("rid");
					String rname = result.getString("rname");
					
					rests = rests + "<a href=\"Updatereview2?restid=" + restid + "\">" + rname + "</a><br><br>";
				}
			
		
				conn.close();
				
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      

	      request.setAttribute("rests", rests);
	    
		        
	      
	      getServletContext()
	      	.getRequestDispatcher("/Updatereviewdisp.jsp")
	      	.forward(request, response);
	 
	   }
	  
	  public void doPost(HttpServletRequest request,
              HttpServletResponse response)
      throws ServletException, IOException
      {
		  doGet(request, response);
      }

	   public void destroy() 
	   { 
	     
	   } 

}

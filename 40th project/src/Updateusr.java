

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



@WebServlet("/Updateusr")
public class Updateusr extends HttpServlet {
	
	
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
			
			String nuser = request.getParameter("nemail");
			
			//System.out.println(curuser + " " + nuser);
			
			
			
			
		  try
			{
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
				
		    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

		    	
				//properties for creating connection to Oracle database
				Properties props = new Properties();
				props.setProperty("user", "testdb");
				props.setProperty("password", "password");
			   
		    	
		    	Connection conn = DriverManager.getConnection(url,props);

		    	if(!nuser.isEmpty())
		    	{	
		    		String sql = "update users set uemail = ? where uemail = ?";
					PreparedStatement preStatement = conn.prepareStatement(sql);
					preStatement.setString(1,nuser);
					preStatement.setString(2,curuser);
					preStatement.executeQuery();
					
					String sql2 = "update reviews set user_id = ? where user_id = ?";
					PreparedStatement preStatement2 = conn.prepareStatement(sql2);
					preStatement2.setString(1,nuser);
					preStatement2.setString(2,curuser);
					preStatement2.executeQuery();
					
					String sql3 = "update reviews set rid = user_id||rest_id where user_id = ? ";
					PreparedStatement preStatement3 = conn.prepareStatement(sql3);
					preStatement3.setString(1,nuser);
					preStatement3.executeQuery();
					
		    	}
				
				
				
		
				conn.close();
				
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      

	    //  request.setAttribute("name", name);
	    
		        
	      
	      getServletContext()
	      	.getRequestDispatcher("/home.jsp")
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

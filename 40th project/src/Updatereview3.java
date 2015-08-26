

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



@WebServlet("/Updatereview3")
public class Updatereview3 extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
			HttpSession session = request.getSession();
			
			
			String one = "one";
			session.setAttribute("fromupdatereview3", one );
			
			
			String curuser = (String) session.getAttribute("curuser");
			int currest = (Integer) session.getAttribute("creview");
			
			
			
			String ncomment = request.getParameter("ncomment");
			String temp_nrating = request.getParameter("nrating");
			double nrating = Double.parseDouble(temp_nrating);
			
			//System.out.println(curuser + " " + currest + " " + ncomment + " " + nrating);
			//String rests = "";
			
			
			try
			{
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
				
		    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

		    	
				//properties for creating connection to Oracle database
				Properties props = new Properties();
				props.setProperty("user", "testdb");
				props.setProperty("password", "password");
			   
		    	
		    	Connection conn = DriverManager.getConnection(url,props);

		    	
		    	String rid = curuser + currest;
		    	
		    	String sql = "update reviews set rcomment = ?, rating = ? where rid = ?";
				PreparedStatement preStatement = conn.prepareStatement(sql);
				preStatement.setString(1,ncomment);
				preStatement.setDouble(2,nrating);
				preStatement.setString(3, rid);
				preStatement.executeQuery();
				
		
				conn.close();
				
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
				
			
			
			
			
		  
	      // Set response content type
	      response.setContentType("text/html");
	      

	    //  request.setAttribute("rests", rests);
	    
		        
	      
	      getServletContext()
	      	.getRequestDispatcher("/Userview")
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

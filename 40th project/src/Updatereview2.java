

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



@WebServlet("/Updatereview2")
public class Updatereview2 extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
			HttpSession session = request.getSession();
			
			//String curuser = (String) session.getAttribute("curuser");
			
			//String rests = "";
			
			String temp_rid = request.getParameter("restid");
			int restid = Integer.parseInt(temp_rid);
			
			session.setAttribute("creview", restid );
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      

	    //  request.setAttribute("rests", rests);
	    
		        
	      
	      getServletContext()
	      	.getRequestDispatcher("/Updatereview2disp.jsp")
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

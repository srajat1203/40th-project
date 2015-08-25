

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



@WebServlet("/Restaurant")
public class Restaurant extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
		  String name = "";
		  String address = "";
		  String desc = "";
		  String rev = "";
		  double avg = 0;
		  double count = 0;
		  
			
	
		  
		  
			HttpSession session = request.getSession();
			String curuser = (String) session.getAttribute("curuser");
			//String curusername = (String) session.getAttribute("curusername");
			//System.out.println(curuser);
			
			String temp_restid = request.getParameter("restid");
			int restid = Integer.parseInt(temp_restid);
			//System.out.println(restid);
		  
			session.setAttribute("currest", restid);
			
		  try
			{
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
				
		    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

		    	
				//properties for creating connection to Oracle database
				Properties props = new Properties();
				props.setProperty("user", "testdb");
				props.setProperty("password", "password");
			   
		    	
		    	Connection conn = DriverManager.getConnection(url,props);


		    	String sql = "select * from rests where rid = ?";
		    	
				PreparedStatement preStatement = conn.prepareStatement(sql);

				preStatement.setInt(1,restid);
				
				ResultSet result;
				result = preStatement.executeQuery();
				
				
				if(result.next())
				{
					name = result.getString("rname");
					address = result.getString("raddress");
					desc = result.getString("rdesc");
					
					//System.out.println(name + " " + address + "desc");
						
					String sql2 = "select * from reviews where rest_id = ?";
					
					PreparedStatement preStatement2 = conn.prepareStatement(sql2);

					preStatement2.setInt(1,restid);
					
					ResultSet result2;
					result2 = preStatement2.executeQuery();
					
					double sum = 0;
					while(result2.next())
					{
						count++;
						double rating = result2.getDouble("rating");
						sum = sum + rating;
						
						String date = result2.getString("rdate");
						String comment = result2.getString("rcomment");
						String user = result2.getString("user_id");
						
						rev = rev + comment + "<br>" + date + "<br>" + "<a href=\"Userprofile?userid=" + user + "\">" + user + "</a><br><br>";
						
					}
					
					if(count != 0)
					{
						avg = sum/count;
					}
					
				
				}
				
				
				
				
				/*
		//Weights table reading and writing
				
				//Reading weights table to see if assignment has been added previously
				boolean exists = false;
				String sql2 = "SELECT DISTINCT ASSIGNMENT FROM W";
				PreparedStatement statement = conn.prepareStatement(sql2);
				ResultSet result = statement.executeQuery();
				
				while(result.next())
				{
					 String cura = result.getString("ASSIGNMENT");
					 if(cura == assignment)
					 {
						 exists = true;
						 break;
					 }
				}
				
				//Writing default weights to database if new assignment is created
				if(!exists)
				{
					String sql3 = "INSERT INTO W (ASSIGNMENT, WEIGHT1, WEIGHT2, WEIGHT3, WEIGHT3) " + "VALUES (?, 25, 25, 25, 25)";
					PreparedStatement statement2 = conn.prepareStatement(sql3);
					statement2.setString(1,assignment);
					statement2.executeQuery();
				}
				*/
				
				conn.close();
				
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      

	      request.setAttribute("name", name);
	      request.setAttribute("address", address);
	      request.setAttribute("desc", desc);
	      
	      
	      
	      request.setAttribute("ratings", count);
	      request.setAttribute("avgrating", avg);
	      request.setAttribute("rev", rev);
	      
	      getServletContext()
	      	.getRequestDispatcher("/Restaurantdisp.jsp")
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

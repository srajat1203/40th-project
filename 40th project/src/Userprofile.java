

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



@WebServlet("/Userprofile")
public class Userprofile extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
			HttpSession session = request.getSession();
			
			String userid = request.getParameter("userid");
			//System.out.println(userid);
			
			//String curuser = (String) session.getAttribute("curuser");
			
			//String temp_restid = request.getParameter("restid");
			
			//int restid = Integer.parseInt(temp_restid);
			
		  
			String name = "";
			int zip = 00000;
			String rev = "";
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


		    	String sql = "select * from users where uemail = ? ";
		    	
				PreparedStatement preStatement = conn.prepareStatement(sql);

				preStatement.setString(1,userid);
				
				ResultSet result;
				result = preStatement.executeQuery();
				
				
				if(result.next())
				{
					name = result.getString("uname");
					zip = result.getInt("uzip");
					
					String sql2 = "select rdate, rcomment, rating from reviews where user_id = ?"; 
							
					PreparedStatement preStatement2 = conn.prepareStatement(sql2);

					preStatement2.setString(1,userid);
					
					ResultSet result2;
					result2 = preStatement2.executeQuery();		
					
					while(result2.next())
					{
						String date = result2.getString("rdate");
						String comment = result2.getString("rcomment");
						double rating = result2.getDouble("rating");
						
						rev = rev + date + "<br>" + comment + "<br>" + rating + "<br><br>";
					}
					
					
					String sql3 = "select rests.rname from rests inner join reviews on rests.rid = reviews.rest_id where reviews.user_id = ?"; 
					
					PreparedStatement preStatement3 = conn.prepareStatement(sql3);

					preStatement3.setString(1,userid);
					
					ResultSet result3;
					result3 = preStatement3.executeQuery();		
					
					while(result3.next())
					{
						String rest = result3.getString("rname");
						//System.out.println(rest);
						rests = rests + rest + "<br><br><br><br>";
					}
					
				}
				
				
				
				
		
				conn.close();
				
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      

	      request.setAttribute("name", name);
	      request.setAttribute("zip", zip);
	      request.setAttribute("rev", rev);
	      request.setAttribute("rests", rests);
		        
	      
	      getServletContext()
	      	.getRequestDispatcher("/Userprofiledisp.jsp")
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

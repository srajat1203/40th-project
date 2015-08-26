

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



@WebServlet("/Userview")
public class Userview extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
		  	String name = "";
		  	String rlist = "";
		  	String welcome = "";
		  	String usr = "";
		  	
		  //	String d = request.getParameter("name");
			//  String c = request.getParameter("comment");
			  //String r = request.getParameter("rating");
		  	
		  	
		  	
			String email = request.getParameter("email");
			//if(email != null)
			
				
		
			
			HttpSession session = request.getSession();
			
			if(email != null)
			{
				session.setAttribute("curuser", email);
				usr =  (String) session.getAttribute("curuser");
			}
			else
			{
				String date = request.getParameter("date");
				String comment = request.getParameter("comment");
				String temp_rating = request.getParameter("rating");
				double rating = Double.parseDouble(temp_rating);
				
				
				
				System.out.println(date + " " + comment + " "+ rating);
				int currest =  (int) session.getAttribute("currest");
				usr =  (String) session.getAttribute("curuser");
				
				String rid = currest+usr;
				
				  try
					{
				    	Class.forName("oracle.jdbc.driver.OracleDriver");
						
				    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

				    	
						//properties for creating connection to Oracle database
						Properties props = new Properties();
						props.setProperty("user", "testdb");
						props.setProperty("password", "password");
					   
				    	
				    	Connection conn = DriverManager.getConnection(url,props);

						//String sql ="INSERT INTO students (ID, ASSIGNMENT, ATYPE, ADATE, GRADE) " + "VALUES (?, ?, ?, TO_DATE(?,'YYYY/MM/DD'), ?)";

				    	String sql = "insert into reviews (rid, rest_id, user_id, rdate, rcomment, rating) " + "values (?, ?, ?, ?, ?, ?)";
				    	
						PreparedStatement preStatement = conn.prepareStatement(sql);

						preStatement.setString(1,rid);
						preStatement.setInt(2,currest);
						preStatement.setString(3,usr);
						preStatement.setString(4,date);
						preStatement.setString(5,comment);
						preStatement.setDouble(6,rating);
						
						
						//ResultSet result;
						preStatement.executeQuery();
						
						conn.close();
					}
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				
			}
			
			  
			
			
		  
		  try
			{
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
				
		    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

		    	
				//properties for creating connection to Oracle database
				Properties props = new Properties();
				props.setProperty("user", "testdb");
				props.setProperty("password", "password");
			   
		    	
		    	Connection conn = DriverManager.getConnection(url,props);

				//String sql ="INSERT INTO students (ID, ASSIGNMENT, ATYPE, ADATE, GRADE) " + "VALUES (?, ?, ?, TO_DATE(?,'YYYY/MM/DD'), ?)";

		    	String sql = "select uname from users where uemail = ?";
		    	
				PreparedStatement preStatement = conn.prepareStatement(sql);

				preStatement.setString(1,usr);
				
				ResultSet result;
				result = preStatement.executeQuery();
				
				
				if(result.next())
				{
					name = result.getString("uname");
					welcome = "Hey " + name + ", it's good to see you!";
					//System.out.println(name);
					
					//session.setAttribute("curusername", name);
				
					
					String sql2 = "select rname, rid from rests";
					
					PreparedStatement preStatement2 = conn.prepareStatement(sql2);
					
					ResultSet result2;
					result2 = preStatement2.executeQuery();
					
					
					
					while(result2.next())
					{
						String cur_rest = result2.getString("rname");
						int cur_rid = result2.getInt("rid");
						//System.out.println(cur_rid);
						
						rlist = rlist + "<a href=\"Restaurant?restid=" + cur_rid + "\">" + cur_rest + "</a><br><br>";
						//System.out.println(cur_rest);
					}
					
				}
				else
				{
					welcome = "No Such user exists! Go back to home page and try again";
					rlist = "You cannot view the restaurants without signing in";
				}	
				
		
			     conn.close();
				
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  	
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      
	      request.setAttribute("welcome", welcome);
	      request.setAttribute("rlist", rlist);
	      
	      getServletContext()
	      	.getRequestDispatcher("/Userviewdisp.jsp")
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

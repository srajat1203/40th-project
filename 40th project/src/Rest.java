

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



@WebServlet("/Rest")
public class Rest extends HttpServlet {
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  	
		  	
		  	String temp_rid = request.getParameter("id");
		  	int rid = 0;
		  	if(temp_rid != null)
		  	{
		  		rid = Integer.parseInt(temp_rid);
		  	}
			String rname = request.getParameter("nname");
			String radd = request.getParameter("nadd");
		  	
			//System.out.println(rid);
			//System.out.println(rname);
			//System.out.println(radd);
		  	
		  	
			
			
			//if(email != null)
		
			HttpSession session = request.getSession();
			
			
			
		  
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

		    	String sql = "select * from rests where rid = ?";
		    	
				PreparedStatement preStatement = conn.prepareStatement(sql);

				preStatement.setInt(1,rid);
				
				ResultSet result;
				result = preStatement.executeQuery();
				
				
				if(result.next())
				{
					if(!rname.isEmpty())
					{
						String sql2 = "update rests set rname = ? where rid = ?";
						PreparedStatement preStatement2 = conn.prepareStatement(sql2);
						preStatement2.setString(1, rname);
						preStatement2.setInt(2, rid);
						preStatement2.executeQuery();
					}
					
					if(!radd.isEmpty())
					{
						String sql2 = "update rests set raddress = ? where rid = ?";
						PreparedStatement preStatement2 = conn.prepareStatement(sql2);
						preStatement2.setString(1, radd);
						preStatement2.setInt(2, rid);
						preStatement2.executeQuery();
					}
					
		
			     conn.close();
				}
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  	
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      
	     // request.setAttribute("welcome", welcome);
	     // request.setAttribute("rlist", rlist);
	      
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

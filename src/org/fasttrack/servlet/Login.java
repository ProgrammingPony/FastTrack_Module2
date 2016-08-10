package org.fasttrack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.fasttrack.db.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isAuthentic = false;
		
		//If already logged in redirect
		HttpSession session = request.getSession(false);
		
		if ( (session != null) && (session.getAttribute("username") != null) )
			response.sendRedirect("index.jsp");
		
		//JDBC
		Connection conn = null;
	    Statement stmt = null;
	    
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(DBHelper.getJdbcDriver());

	      //STEP 3: Open a connection
	      conn = DriverManager.getConnection(
	    		  DBHelper.getDbUrl(),
	    		  DBHelper.getUser(),
	    		  DBHelper.getPass()
	    		  );

	      //Check if it is a customer
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM customer WHERE username='" + username + "' AND password='" + password + "'";	      
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      if (rs.next()) {
	    	  isAuthentic = true;
	    	  session = request.getSession();
	    	  session.setAttribute("username", username);
	    	  session.setAttribute("id", rs.getInt("customerId"));
	    	  session.setAttribute("role", "customer");    	  
	      }
	      
	      rs.close();
	      stmt.close();
	      
	      //Check if it is a manager if its not a customer
	      if (!isAuthentic) {
	    	  stmt = conn.createStatement();
	    	  
	    	  sql = "SELECT * FROM manager WHERE username='" + username + "' AND password='" + password + "'";
	    	  
	    	  rs = stmt.executeQuery(sql);
		      
		      if (rs.next()) {
		    	  isAuthentic = true;
		    	  session.setAttribute("username", username);
		    	  
		    	  if (rs.getInt("adminstatus") != 1)
		    		  session.setAttribute("role", "manager");
		    	  else
		    	   	  session.setAttribute("role", "admin");
		    	  
		    	  session.setAttribute("id", rs.getInt("managerId"));
		      }
		      
		      rs.close();
		      stmt.close();
	      }
	      
	      conn.close();
	      
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
		
		
		//Redirect
	   if (isAuthentic) {
		   getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
	   } else {
		   request.setAttribute("username", username);
		   request.setAttribute("login-error", "No username or password was found to match this user");
		   getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
	   }
		
	}

}

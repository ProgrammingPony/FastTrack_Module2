package org.fasttrack.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fasttrack.db.DBHelper;

/**
 * Servlet implementation class MyPolicies
 */
@WebServlet("/MyPolicies")
public class MyPolicies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPolicies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		//Redirect if no session or username not set, or role is not customer
		if (session == null || session.getAttribute("username") == null || !((String)session.getAttribute("role")).equals("customer") ) {
			request.setAttribute("login-error", "This page requires the user to log in");
			getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
		}
		
		//Get parameters
		String username = (String) session.getAttribute("username");
		int id = (int) session.getAttribute("id");
		
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
	      sql = "SELECT * FROM mapping WHERE customerId=" + id + " AND username='" + username + "'";	      
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      ArrayList< TreeMap<String, Object> > list = new ArrayList< TreeMap<String, Object> > ();
	      
	      //Make list containing all the user's policies
	      while (rs.next()) {
	    	  //Map with name,id of each Policy
	    	  TreeMap<String, Object> map = new TreeMap<String, Object>();
	    	  map.put("id", value);
	    	  map.put("name", value);
	    	  list.add(map);
	      }
	      
	      rs.close();
	      stmt.close();
	      
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
	    
	    getServletContext().getRequestDispatcher("/my-policies.jsp").include(request, response);
	    
	}
}

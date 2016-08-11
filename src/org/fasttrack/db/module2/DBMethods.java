package org.fasttrack.db.module2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.*;
import org.fasttrack.processing.DateHelper;



public class DBMethods {

	private DBMethods() {}
	
	public static boolean login(HttpServletRequest request) {
		
		int id= Integer.parseInt(request.getParameter("username"));
		String password = request.getParameter("password");
		
		boolean isAuthentic = false;
		
		
		HttpSession session = request.getSession();
		Connection conn = null;
	    Statement stmt = null;
	    
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(DBHelper.getJdbcDriver());
	      conn = DBHelper.getDatabaseConnection();

	      //Check if it is a customer
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM customer WHERE customerId=" + id + " AND password='" + password + "'";	      
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      if (rs.next()) {
	    	  isAuthentic = true;
	    	  session.setAttribute("id", rs.getInt("customerId"));
	    	  session.setAttribute("role", "customer");    	  
	      }
	      
	      rs.close();
	      stmt.close();
	      
	      //Check if it is a manager if its not a customer
	      if (!isAuthentic) {
	    	  stmt = conn.createStatement();
	    	  
	    	  sql = "SELECT * FROM manager WHERE managerId=" + id + " AND password='" + password + "'";
	    	  
	    	  rs = stmt.executeQuery(sql);
		      
		      if (rs.next()) {
		    	  isAuthentic = true;
		    	  
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
		
	   return isAuthentic;
	}
	
	public static void myPolicies (HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		//Get parameters
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
	      sql = "SELECT policy.policyId as id, policyName, tenureYears, acceptancedate, premium, sumAssured  FROM mapping LEFT JOIN policy ON policy.policyId = mapping.policyId WHERE customerId=" + id;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      ArrayList< TreeMap<String, Object> > list = new ArrayList< TreeMap<String, Object> > ();
	      TreeMap<String, Object> map;
	      //Make list containing all the user's policies
	      while (rs.next()) {
	    	  //Map with name,id of each Policy
	    	  map = new TreeMap<String, Object>();
	    	  map.put("id", rs.getInt("id"));
	    	  map.put("name", rs.getString("policyName"));
	    	  map.put("sumAssured", rs.getInt("sumAssured"));
	    	  
	    	  int tenureYears = rs.getInt("tenureYears");
	    	  
	    	  map.put("tenure", tenureYears);
	    	  map.put("premium", rs.getInt("premium"));
	    	  map.put("expiry", 
	    			  DateHelper.addYearsToDate( rs.getDate("acceptanceDate"), tenureYears ).toString()
	    			  );
	    	  
	    	  list.add(map);
	      }
	      
	      request.setAttribute("my-policy-list", list);
	      
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
	}
	
}

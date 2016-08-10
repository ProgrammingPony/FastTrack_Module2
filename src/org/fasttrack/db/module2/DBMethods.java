package org.fasttrack.db.module2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.DBHelper;

public class DBMethods {

	private DBMethods() {}
	
	public static boolean login(HttpServletRequest request, int id, String password) {
		
		boolean isAuthentic = false;
		
		HttpSession session = request.getSession();
		
		//JDBC
		Connection conn = DBHelper.getDatabaseConnection();
	    Statement stmt = null;
	    
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(DBHelper.getJdbcDriver());

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
	
}

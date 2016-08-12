package org.fasttrack.jdbc;


import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.DBHelperMuhsanah;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDeactivatePolicy1 {


	public static boolean checkPolicy(int p) {

		boolean policyExist = false;
		
		System.out.println("in check policy");

			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      String query ="Select * from Policy where policyId=?";
		      stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
		      
		      
		      ResultSet rs = stmt.executeQuery();

			  if(rs.next()){ // policy exist
			  	policyExist = true;
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
		
		System.out.println("print policy exist "+policyExist);
		
	   return policyExist;
	}
	

	// deactivate policy
	public static boolean deactivatePolicy(int p) {

		boolean success = false;
			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      // update table
		      // set policy as not active
		      String query = "UPDATE Policy SET isActive=? where policyId =? ";
			  stmt = conn.prepareStatement(query);
			  stmt.setString(1,"N");
			  stmt.setInt(2, p);
			  ResultSet rs = stmt.executeQuery();

			  
			  // Check if it was updated successfully

			  query = "SELECT * FROM Policy where isActive=? and policyId=?";
			  stmt = conn.prepareStatement(query);
			  stmt.setString(1,"N");
			  stmt.setInt(2, p);
			  rs = stmt.executeQuery();

			  if(rs.next()){ //updated successfully
			  	success = true;
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
		
	   return success;
	}
}

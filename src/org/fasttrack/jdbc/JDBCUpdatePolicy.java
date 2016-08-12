package org.fasttrack.jdbc;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.DBHelperMuhsanah;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUpdatePolicy {

	// cehck if policy id exist
	public static boolean checkPolicy(int p) {

		boolean policyExist = false;
			
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
		
	   return policyExist;
	}


	// Array with all tenure length for a policy
	public static ArrayList<Integer> getTenureArray(int p){
		ArrayList<Integer> list = new ArrayList<Integer>();

			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      String query ="Select * from policyHasTenure where policyId=?";
		      stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
		      
		      
		      ResultSet rs = stmt.executeQuery();

			  while(rs.next()){ // policy has tenure length exist
			  	list.add(rs.getInt("tenureLength"));
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
	   }// end try

		return list;


	}

	// check if policy Name exist
	public static boolean checkPolicyName(String policyName, int p) {

		boolean policyNameExist = false;

			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      String query = "Select * from policy where policyName=?";
			  stmt = conn.prepareStatement(query);
			  stmt.setString(1, policyName);
			  ResultSet rs = stmt.executeQuery();
			
			
			  while(rs.next()){ // cehck if any other policy has that name
				  if(rs.getInt("policyId") != p){
					policyNameExist = true;
				  }
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
		
	   return policyNameExist;
	}


	// update policy table
	public static boolean updatePolicy(String policy_name2, int policy_sumMin2, int policy_sumMax2, String policy_prereq2, int p, int policy_nom2) {

		boolean success = false;
			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      // Update Policy Table
				String query = "UPDATE Policy SET policyName=?, sumAssuredMin=?, sumAssuredMax=?, nomineeMin=?, prereq=?  where policyId =? ";

				// Create a statement
				stmt = conn.prepareStatement(query);
				stmt.setString(1, policy_name2);
				stmt.setInt(2, policy_sumMin2);
				stmt.setInt(3, policy_sumMax2);
				stmt.setInt(4, policy_nom2);
				stmt.setString(5, policy_prereq2);
				stmt.setInt(6, p);

				// Execute the query
				ResultSet rs = stmt.executeQuery();

				//check if updated successfully 

				query = "SELECT * FROM Policy where policyName=? and sumAssuredMin=? and sumAssuredMax=? and nomineeMin=? and prereq=? and policyId =?";

				// Create a statement
				stmt = conn.prepareStatement(query);
				stmt.setString(1, policy_name2);
				stmt.setInt(2, policy_sumMin2);
				stmt.setInt(3, policy_sumMax2);
				stmt.setInt(4, policy_nom2);
				stmt.setString(5, policy_prereq2);
				stmt.setInt(6, p);

				// Execute the query
				rs = stmt.executeQuery();
			
			
			  if(rs.next()){ // successfully updated
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



	// delete tenure
	public static boolean deleteTenure(int p) {

		boolean success = true;
			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      // Delete entries from policyHasTenure
				 String query = "DELETE FROM policyHasTenure WHERE policyId=?";
				 stmt = conn.prepareStatement(query);
				 stmt.setInt(1, p);
				 
				 ResultSet rs = stmt.executeQuery();

				//check if deleted successfully 

				query = "SELECT * FROM policyHasTenure where policyId =?";

				// Create a statement
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, p);

				// Execute the query
				rs = stmt.executeQuery();
			
			
			  if(rs.next()){ // failed to remove tenure
				  success = false;
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


	// Insert tenure in policyHasTenure table
	public static boolean addTenure(int p, int year) {

		boolean success = false;
			
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

		      String query = "INSERT into policyHasTenure values (?,?)";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
		      stmt.setInt(2, year);
			  ResultSet rs = stmt.executeQuery();

			  // Check if policy tenure was inserted

			  query = "Select * from policyHasTenure where policyId=? and tenureLength=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
		      stmt.setInt(2, year);
			  rs = stmt.executeQuery();
			
			
			  if(rs.next()){ // successfully added tenure
				  success=true;
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

	// return policy name
	public static String getPolicyName(int p) {
		String name = null;
		
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

			  // Check if policy exist

			  String query = "Select * from Policy where policyId=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
			  ResultSet rs = stmt.executeQuery();
			
			  if(rs.next()){ // if policy exist
				  name = rs.getString("policyName");
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
		return name;
	}
	
	// return policy nominee num
	public static int getPolicyNom(int p) {
		int nom = 0;
		
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

			  // Check if policy exist

			  String query = "Select * from Policy where policyId=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
			  ResultSet rs = stmt.executeQuery();
			
			  if(rs.next()){ // if policy exist
				  nom = rs.getInt("nomineeMin");
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
		return nom;
	}
	
	
	// return sum min
	public static int getPolicySumMin(int p) {
		int min = 0;
		
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

			  // Check if policy exist

			  String query = "Select * from Policy where policyId=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
			  ResultSet rs = stmt.executeQuery();
			
			  if(rs.next()){ // if policy exist
				  min = rs.getInt("sumAssuredMin");
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
		return min;
	}
	
	// return sum max
	public static int getPolicySumMax(int p) {
		int max = 0;
		
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

			  // Check if policy exist

			  String query = "Select * from Policy where policyId=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
			  ResultSet rs = stmt.executeQuery();
			
			  if(rs.next()){ // if policy exist
				  max = rs.getInt("sumAssuredMax");
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
		return max;
	}
	
	// return policy prereq
	public static String getPolicyPrereq(int p) {
		String prereq = null;
		
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

			  // Check if policy exist

			  String query = "Select * from Policy where policyId=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
			  ResultSet rs = stmt.executeQuery();
			
			  if(rs.next()){ // if policy exist
				  prereq = rs.getString("prereq");
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
		return prereq;
	}
	
	// get Policy isActive
	public static String getPolicyActive(int p) {
		String active = null;
		
		//Connection
		Connection conn = DBHelperMuhsanah.getDatabaseConnection();
		PreparedStatement stmt = null;


		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelperMuhsanah.getJdbcDriver());

			  // Check if policy exist

			  String query = "Select * from Policy where policyId=?";
			  stmt = conn.prepareStatement(query);
		      stmt.setInt(1, p);
			  ResultSet rs = stmt.executeQuery();
			
			  if(rs.next()){ // if policy exist
				  active = rs.getString("isActive");
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
		return active;
	}
	
}



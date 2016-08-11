package org.fasttrack.db.module2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.*;
import org.fasttrack.db.sequences.PolicySequence;
import org.fasttrack.jdbc.JDBCDemo;
import org.fasttrack.processing.DateHelper;



public class DBMethods {

	private DBMethods() {}
	
	static JDBCDemo jdbc = new JDBCDemo();
	static int policyIdSeq = 100;
	static PolicySequence seq = new PolicySequence();
	
	public static void RegisterPolicy (HttpServletRequest request, HttpServletResponse response) {
		
		
			int id = -1;
			
			try {
				id = seq.getNextId();
			} catch (SQLException e1) {
				System.out.println("Was not able to fetch the next policy id");
				e1.printStackTrace();
				return;
			}
			
			int[] elements = new int[6];
			String policyName = request.getParameter("name");
			String policyNominee = request.getParameter("numNominee");
			String policyPre = request.getParameter("prereq");
			String policyMin = request.getParameter("minSum");
			String policyMax = request.getParameter("maxSum");
			
			int policyMaxNum =0;
			int policyNomineeNum =0;
			int policyMinNum = 0;
			
			boolean i = false;
			boolean j = false;
			//out.println("i am here 2 ");
			String errorMessage = null;
			// validations!!! //////////
			String tenure;
			int counter =0;
			for(int k = 1; k<7; k++){
				tenure = request.getParameter("tenure"+k);
				System.out.println(tenure);
			if(tenure != null){
				elements[k-1] = 1;
				counter++;
				
			}}
			if(counter ==0){
				i = false;
				errorMessage = " chose at least 1 tenure";
				request.setAttribute("error", errorMessage);
			}
			// for name//
			try {
				i = jdbc.check(policyName);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("here after checking for the name.....");
			// for min max and num nominee...////
			try{
				policyMinNum = Integer.parseInt(policyMin);
			}
			catch(NumberFormatException e){
				i = false;
				errorMessage = " enter valid Minimum assured sum";
				request.setAttribute("error", errorMessage);
			}
			
			try{
				policyMaxNum = Integer.parseInt(policyMax);
			}
			catch(NumberFormatException e){
				i = false;
				errorMessage = " enter valid Maximum assured sum";
				request.setAttribute("error", errorMessage);
			}
			
			try{
				policyNomineeNum = Integer.parseInt(policyNominee);
			}
			catch(NumberFormatException e){
				i = false;
				errorMessage = " enter valid minimum number of nomineeNominee";
				request.setAttribute("error", errorMessage);
			}
			
			if(policyMaxNum<policyMinNum||policyMaxNum<0 ||policyMinNum<0 ){
				i = false;
				errorMessage = " enter valid assured sums";
				request.setAttribute("error", errorMessage);
			}
			
			if(i == false){
				if(errorMessage == null){
					errorMessage = "The policy with the same name exists in the system";
					request.setAttribute("error", errorMessage);
				}
				request.setAttribute("name", policyName);
				request.setAttribute("numNominee", policyNominee);
				request.setAttribute("prereq", policyPre);
				request.setAttribute("minSum", policyMin);
				request.setAttribute("maxSum", policyMax);
				
				/*
				try {
					//response.sendRedirect("/index.jsp");
					//getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
					return;
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		
			}		
			System.out.println("before insering");
			// validations end here!!! //////////
			
			if(i == true){
				
				policyIdSeq++;
				
				//  insert into the database..
				try {
					j = jdbc.insertPolicy(id,policyName, policyMinNum, policyMaxNum, policyNomineeNum, policyPre, elements);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("here after inserting!!");
			if(j == true){
				// message for back... as success.. put a button to go back to home..
				request.setAttribute("Success", "Policy was created Successfully");
				//response.sendRedirect("Index.jsp");
				System.out.println("j true");
				
			}
	}
	
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

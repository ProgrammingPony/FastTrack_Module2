package org.fasttrack.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.fasttrack.db.DBHelper;

import java.sql.*;

@ManagedBean(name="viewCustomersMarkedToAgent")
@SessionScoped
public class ViewCustomersMarkedToAgent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int agentId;
	private int customerId = -1;	
	private int policyId = -1;
	private String policyName = "";
	
	private List<Integer> customerIds;
	private List<String> policyNames;
	private List<Integer> policyIds;
	private List<Integer> agentIds;
	
	private int tenure;
	private int sumAssured;
	private int premium;
	
	
	
	
	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public int getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(int sumAssured) {
		this.sumAssured = sumAssured;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public List<Integer> getPolicyIds() {
		return policyIds;
	}

	public void setPolicyIds(List<Integer> policyIds) {
		this.policyIds = policyIds;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public List<Integer> getAgentIds() {
		
		Connection conn = null;
		Statement stmt = null;
		List<Integer> agentList = new ArrayList<Integer>();
		
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelper.getJdbcDriver());
		      conn = DBHelper.getDatabaseConnection();

		      //Check if it is a customer
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT agentId FROM agent";	      
		      
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while (rs.next()) {
		    	  agentList.add( rs.getInt("agentId") );
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
		   }
		
		agentIds = agentList;
		
		return agentIds;
	}

	public void setAgentIds(List<Integer> agentIds) {
		this.agentIds = agentIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getPolicyNames() {
		return policyNames;
	}

	public void setPolicyNames(List<String> policyNames) {
		this.policyNames = policyNames;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
	public String updateCustomerList() {
		
		Connection conn = null;
		Statement stmt = null;
		List<Integer> customerList = new ArrayList<Integer>();
		
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelper.getJdbcDriver());
		      conn = DBHelper.getDatabaseConnection();

		      //Check if it is a customer
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT customerId FROM mapping WHERE agentId=" + agentId;	      
		      
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while (rs.next()) {
		    	  customerList.add( rs.getInt("customerId") );
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
		   }
		
		customerIds = customerList;
		
		return "Done Updating List";
	}
	
	public String viewPolicyDetails() {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelper.getJdbcDriver());
		      conn = DBHelper.getDatabaseConnection();

		      //Check if it is a customer
		      stmt = conn.createStatement();
		      String sql;
		      System.out.println(customerId);
		      sql = "SELECT * FROM MAPPING WHERE CUSTOMERID=" + customerId + " AND POLICYID=" + policyId;
		      
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while (rs.next()) {
		    	  tenure = rs.getInt("tenureyears");
		    	  sumAssured = rs.getInt("sumAssured");
		    	  premium = rs.getInt("premium");
		    	  policyId = rs.getInt("policyId");
		    	  
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
		   }		
		
		return "Done Updating List";
	}
	
	public String updatePolicyList() {
		
		Connection conn = null;
		Statement stmt = null;
		List<String> policyNameList = new ArrayList<String>();
		List<Integer> policyIdList = new ArrayList<Integer>();
		
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DBHelper.getJdbcDriver());
		      conn = DBHelper.getDatabaseConnection();

		      //Check if it is a customer
		      stmt = conn.createStatement();
		      String sql;
		      System.out.println(customerId);
		      sql = "SELECT policyName, policy.policyId as policyId FROM mapping LEFT JOIN policy ON policy.policyId = mapping.policyId WHERE customerId =" + customerId;	      
		      
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while (rs.next()) {
		    	  policyNameList.add( rs.getString("policyName") );
		    	  policyIdList.add( rs.getInt("policyId") );
		    	  
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
		   }
		
			policyIds = policyIdList;
			policyNames = policyNameList;
		
		return "Done Updating Policy List";
	}

}
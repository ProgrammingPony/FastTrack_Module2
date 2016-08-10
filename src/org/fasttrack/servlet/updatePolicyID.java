package org.fasttrack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updatePolicyID
 */
@WebServlet("/updatePolicyID")
public class updatePolicyID extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static Connection conn = null;
	static String url = "jdbc:oracle:thin:@10.101.121.141:1521:xe";
	static String username = "Toronto3";
	static String password = "OraclePass";
	static PreparedStatement stmt = null;
	static String query = null;
	static ResultSet rs = null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePolicyID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			checkLogin(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		String policyId = request.getParameter("policyId");
		System.out.println( request.getParameter("policyId"));
		
		try {
			
			int p = Integer.valueOf(request.getParameter("policyId").trim()); // to int
			
			if(policyId != null) { 
				// Register Driver
				Class.forName(driver);
				
				// Establish Connection
				conn = DriverManager.getConnection(url, username, password);
				
				// Policy Table
				query = "Select * from Policy where policyId=?";

				// Create a statement
				stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, p);
		
			    // Execute the query
			    rs = stmt.executeQuery();
			    
			    // Process result set
				if(rs.next()){ // policyId exists 
					// update policy
					
					HttpSession hsession = request.getSession();
					hsession.setAttribute("policy_id2", rs.getInt("policyId"));
					hsession.setAttribute("policy_name2", rs.getString("policyName"));
					hsession.setAttribute("policy_nom2", rs.getInt("minNumberOfNominees"));
					hsession.setAttribute("policy_sumMin2", rs.getInt("sumAssuredMin"));
					hsession.setAttribute("policy_sumMax2", rs.getInt("sumAssuredMax"));
					hsession.setAttribute("policy_prereq2", rs.getString("prereq"));
					hsession.setAttribute("policy_isActive2", rs.getString("isActive"));
					hsession.setAttribute("policy_ID2", policyId);
					
					// policy HasTenure Table
					//query = "Select * from policyHasTenure where policyId=?";
					// Create a statement
					//stmt = conn.prepareStatement(query);
					//stmt.setInt(1, p);
					 // Execute the query
				    //rs = stmt.executeQuery();
				    /*ArrayList<String> list = new ArrayList<String>();
				    
				    // add tenure length to list
				    while(rs.next()){
				    	list.add(rs.getString("tenureLength"));
				    }
				    hsession.setAttribute("tenure_list2", list);*/
				    
				    response.sendRedirect("updatePolicy.jsp");
				    
				}
		
				else{ // policy ID does not exist
					HttpSession hsession = request.getSession();
					hsession.setAttribute("error_msg2", "Invalid Policy ID");
					hsession.setAttribute("policy_ID2", policyId);
			    	response.sendRedirect("updatePolicy.jsp");
			    }
			    
			    rs.close();
			    stmt.close();
			    conn.close();
		    
			} else { // an empty field
				HttpSession hsession = request.getSession();
				hsession.setAttribute("error_msg2", "Type in all the fields");
				hsession.setAttribute("policy_ID2", policyId);
		    	response.sendRedirect("updatePolicy.jsp");
			}
			
		} catch(Exception e){ // not an int
			HttpSession hsession = request.getSession();
			hsession.setAttribute("error_msg2", "Invalid Policy ID");
			hsession.setAttribute("policy_ID2", policyId);
	    	response.sendRedirect("updatePolicy.jsp");
		}

		


	}

}




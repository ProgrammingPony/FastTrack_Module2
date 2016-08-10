package org.fasttrack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

/**
 * Servlet implementation class deactivatePolicy
 */
@WebServlet("/deactivatePolicy")
public class deactivatePolicy extends HttpServlet {
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
     * Default constructor. 
     */
    public deactivatePolicy() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				query = "Select * from Policy where policyId=?";

				// Create a statement
				stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, p);
		
			    // Execute the query
			    rs = stmt.executeQuery();
			    
			    // Process result set
				if(rs.next()){ // policyId exists 
					// update policy
					
					
					
					query = "UPDATE Policy SET isActive=? where policyId =? ";
					stmt = conn.prepareStatement(query);
					stmt.setString(1,"N");
					stmt.setInt(2, p);
					rs = stmt.executeQuery();
					
					HttpSession hsession = request.getSession();
					//hsession.setAttribute("username", request.getParameter("username"));
					hsession.setAttribute("error_msg2", "Successfully deactivated the policy");
					response.sendRedirect("deactivatePolicy.jsp");
				}
		
				else{ // policy ID does not exist
					HttpSession hsession = request.getSession();
					hsession.setAttribute("error_msg2", "Invalid Policy ID");
					hsession.setAttribute("policy_ID2", policyId);
			    	response.sendRedirect("deactivatePolicy.jsp");
			    }
			    
			    rs.close();
			    stmt.close();
			    conn.close();
		    
			} else {
				HttpSession hsession = request.getSession();
				hsession.setAttribute("error_msg2", "Type in all the fields");
				hsession.setAttribute("policy_ID2", policyId);
		    	response.sendRedirect("deactivatePolicy.jsp");
			}
			
		} catch(Exception e){ // not an int
			HttpSession hsession = request.getSession();
			hsession.setAttribute("error_msg2", "Invalid Policy ID");
			hsession.setAttribute("policy_ID2", policyId);
	    	response.sendRedirect("deactivatePolicy.jsp");
		}

		


	}

}

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

/**
 * Servlet implementation class updatePolicy
 */
@WebServlet("/updatePolicy")
public class updatePolicy extends HttpServlet {
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
	public updatePolicy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

	public static void checkLogin(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, IOException {
		String policyId = request.getParameter("policy_id2");
		String policyNom = request.getParameter("policy_nom2").trim();
		String policySumMax = request.getParameter("policy_sumMax2").trim();
		String policySumMin = request.getParameter("policy_sumMin2").trim();
		String policy_name2 = request.getParameter("policy_name2").trim();
		String policy_prereq2 = request.getParameter("policy_prereq2").trim();
		String policy_isActive2 = request.getParameter("policy_isActive2").trim();
		System.out.println("policy sum max: "+policySumMax);

	
		if (policyId.length() != 0 && policy_name2.length() != 0
					&& policy_prereq2.length() != 0 && policyNom.length() != 0
					&& policySumMax.length() != 0 && policySumMin.length() != 0) {
				
			try {
					
				int p = Integer.valueOf(request.getParameter("policy_id2").trim()); // to
				int policy_nom2 = Integer.valueOf(request.getParameter("policy_nom2").trim());
				int policy_sumMax2 = Integer.valueOf(request.getParameter("policy_sumMax2").trim());
				int policy_sumMin2 = Integer.valueOf(request.getParameter("policy_sumMin2").trim());
				
				if(policy_sumMin2 <= policy_sumMax2){

					// Register Driver
					Class.forName(driver);
	
					// Establish Connection
					conn = DriverManager.getConnection(url, username, password);
					query = "UPDATE Policy SET policyName=?, sumAssuredMin=?, sumAssuredMax=?, minNumberOfNominees=?, prereq=?  where policyId =? ";
	
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
	
					// Process result set
					if (rs.next()) { // policyId exists
						// update policy
	
						HttpSession hsession = request.getSession();
						hsession.setAttribute("error_msg2",
								"Successfully updated the policy");
						response.sendRedirect("updatePolicy.jsp");
					}
	
					else { // policy ID does not exist
						HttpSession hsession = request.getSession();
						hsession.setAttribute("error_msg2", "Invalid Inputs");
						
						hsession.setAttribute("policy_id2", policyId);
						hsession.setAttribute("policy_name2", policy_name2);
						hsession.setAttribute("policy_nom2", policyNom);
						hsession.setAttribute("policy_sumMin2", policySumMin);
						hsession.setAttribute("policy_sumMax2", policySumMax);
						hsession.setAttribute("policy_prereq2", policy_prereq2);
						hsession.setAttribute("policy_isActive2", policy_isActive2);
						
						response.sendRedirect("updatePolicy.jsp");
					}
	
					rs.close();
					stmt.close();
					conn.close();
				}
				else{ // sum Min greater than max
					HttpSession hsession = request.getSession();
					hsession.setAttribute("error_msg2", "Sum assured minimum should be less than sum assured maximum");
					
					hsession.setAttribute("policy_id2", policyId);
					hsession.setAttribute("policy_name2", policy_name2);
					hsession.setAttribute("policy_nom2", policyNom);
					hsession.setAttribute("policy_sumMin2", policySumMin);
					hsession.setAttribute("policy_sumMax2", policySumMax);
					hsession.setAttribute("policy_prereq2", policy_prereq2);
					hsession.setAttribute("policy_isActive2", policy_isActive2);
					
					response.sendRedirect("updatePolicy.jsp");
				}
			} catch (Exception e) { // not an int
				HttpSession hsession = request.getSession();
				hsession.setAttribute("error_msg2", "Invalid Inputs");
				
				hsession.setAttribute("policy_id2", policyId);
				hsession.setAttribute("policy_name2", policy_name2);
				hsession.setAttribute("policy_nom2", policyNom);
				hsession.setAttribute("policy_sumMin2", policySumMin);
				hsession.setAttribute("policy_sumMax2", policySumMax);
				hsession.setAttribute("policy_prereq2", policy_prereq2);
				hsession.setAttribute("policy_isActive2", policy_isActive2);
				
				response.sendRedirect("updatePolicy.jsp");
			}

		} else {
			HttpSession hsession = request.getSession();
			hsession.setAttribute("error_msg2", "Type in all the fields");
			
			hsession.setAttribute("policy_id2", policyId);
			hsession.setAttribute("policy_name2", policy_name2);
			hsession.setAttribute("policy_nom2", policyNom);
			hsession.setAttribute("policy_sumMin2", policySumMin);
			hsession.setAttribute("policy_sumMax2", policySumMax);
			hsession.setAttribute("policy_prereq2", policy_prereq2);
			hsession.setAttribute("policy_isActive2", policy_isActive2);
			
			response.sendRedirect("updatePolicy.jsp");
		}


	}

}

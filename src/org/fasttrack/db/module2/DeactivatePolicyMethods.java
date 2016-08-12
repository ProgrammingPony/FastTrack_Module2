package org.fasttrack.db.module2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.DBHelperMuhsanah;
import org.fasttrack.jdbc.JDBCDeactivatePolicy1;


public class DeactivatePolicyMethods {
	static JDBCDeactivatePolicy1 jdbc = new JDBCDeactivatePolicy1();

	
	public static void deactivatePolicy(HttpServletRequest request, HttpServletResponse response){
		
		String policyId = request.getParameter("policyId");
		System.out.println("In deacPolMethods "+ request.getParameter("policyId"));
		
		//Redirect if no session or username not set, or role is not customer
		/*if (session == null || session.getAttribute("id") == null || !((String)session.getAttribute("role")).equals("customer") ) {
			request.setAttribute("login-error", "This page requires the user to log in");
			getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
		}*/

		try {
			int p = Integer.valueOf(request.getParameter("policyId").trim()); // to int
			
			if(policyId != null) { 


				//Check if policy Exists
				boolean policyExist = JDBCDeactivatePolicy1.checkPolicy(p);


			    // Process result set
				if(policyExist){ // policyId exists 
					// update policy

					boolean deactivateSuccess = JDBCDeactivatePolicy1.deactivatePolicy(p);

					if(deactivateSuccess){ // successfully deactiavted
					
						HttpSession hsession = request.getSession();
						//hsession.setAttribute("username", request.getParameter("username"));
						hsession.setAttribute("error_msg2", "Successfully deactivated the policy");
						System.out.println("in deactivate success");
						
					}
					else{
						HttpSession hsession = request.getSession();
						hsession.setAttribute("error_msg2", "Error in deactivating policy");
						hsession.setAttribute("policy_ID2", policyId);
					}
				}
		
				else{ // policy ID does not exist
					HttpSession hsession = request.getSession();
					hsession.setAttribute("error_msg2", "Invalid Policy ID");
					hsession.setAttribute("policy_ID2", policyId);
			    
			    }
			    
		    
			} else { // policyId field null
				HttpSession hsession = request.getSession();
				hsession.setAttribute("error_msg2", "Type in all the fields");
				hsession.setAttribute("policy_ID2", policyId);
		    	
			}
			
		} catch(NumberFormatException e){ // not an int
			HttpSession hsession = request.getSession();
			hsession.setAttribute("error_msg2", "Invalid Policy ID");
			hsession.setAttribute("policy_ID2", policyId);
	    	
		}

	}
}
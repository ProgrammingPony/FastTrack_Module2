package org.fasttrack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateNomineeServlet
 */
@WebServlet("/updateNomineeServlet")
public class updateNomineeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		int nomineeId = Integer.parseInt(request.getParameter("nomineeID"));
		String nomineeName = request.getParameter("newNomineeName"); 
		String relation = request.getParameter("newRelationToNominee");
		String dob =request.getParameter("newdob");
		String purpose = request.getParameter("newNomineePurpose"); 
		
		if(updateNomineeJDBC.update(nomineeId, nomineeName, relation, dob)){
			RequestDispatcher rs = request.getRequestDispatcher("updateNomineeSuccess.jsp");
			rs.forward(request, response);
		}
		else{
		//	RequestDispatcher rs2 = request.getRequestDispatcher("failure.jsp");
			//rs2.forward(request, response);
			
		
	}
		

	}
}

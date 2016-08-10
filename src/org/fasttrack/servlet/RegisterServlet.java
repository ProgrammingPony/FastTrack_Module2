package org.fasttrack.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fasttrack.jdbc.JDBCDemo;

import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;
	static JDBCDemo jdbc = new JDBCDemo();
	static int policyIdSeq = 100;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub

		
		response.setContentType ("Text/html");
		System.out.println("hi parul");
		//PrintWriter out = response.getWriter();
		System.out.println("inside Servlet1");
		

		
		System.out.println("i am here 1 ");
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
		for(int k = 1; k<7; k++){
			tenure = request.getParameter("tenure"+k);
		if(tenure != null){
			elements[k-1] = 1;
			
		}}
		
		// for name//
		try {
			i = jdbc.check(policyName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			
			
			try {
				getServletContext().getRequestDispatcher("/policy-register.jsp").include(request, response);
				return;
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}		
		
		// validations end here!!! //////////
		
		if(i == true){
			
		
			
			int policyId = policyIdSeq;
			policyIdSeq++;
			
			// have to insert into the database..
			try {
				j = jdbc.insertPolicy(policyId,policyName, policyMinNum, policyMaxNum, policyNomineeNum, policyPre, elements);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		if(j == true){
			
			response.sendRedirect("success.html");
			System.out.println("j true");
			
		}
		else{
			
			response.sendRedirect("success.html");
			System.out.println("j false");
		}
		
		
	}

}


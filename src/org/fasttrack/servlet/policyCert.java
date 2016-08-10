package org.fasttrack.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class policyCert
 */
@WebServlet("/policyCert")
public class policyCert extends HttpServlet {

	
	Connection con=null;
	ResultSet rs;
	PreparedStatement ps=null;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/PolicyCertificate.jsp");
		rd.include(request, response);	
		
		ArrayList resultset= new ArrayList();
		response.setContentType("text/html");
		//HttpSession hsession=request.getSession();
		//hsession.setAttribute("Pageuser", request.getParameter("user"));
		
		
		try{
						
					
					String uname;
					String policy;
					double Policyid=0;
					String Policyname="";
					String Policyten;
					String Policyprem;
					String nominee;
					
					PrintWriter out= response.getWriter();
					out= response.getWriter();
		
					String query="select * from policy where PolicyName = ?";
					String query2="select * from nominee where customerid = ?";
					String Pass="tcs12345";
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user="system";
					response.setContentType("text/html");
					
					
					
				//request.getParameter("dd1");
					 
				
			policy=(String)request.getParameter("pselect");
			
			//policy=(String)request.getParameter("dl");
				
			Class.forName("oracle.jdbc.OracleDriver");

			con=DriverManager.getConnection(url,user,Pass);
		
			ps=con.prepareStatement(query);
			
			//ps.setString(1, uname);
			//ps.setString(2, pass);
			
			ps.setString(1, policy);
			//out.println(policy);
			rs=ps.executeQuery();
			
			
		//	if(uname.equals("Manager"))
		//
		//	out.println("Please enter the customer id");
		//}
		//	else{
			
			while(rs.next())
			{	 
					Policyid=rs.getDouble(1);
					Policyname=rs.getString(2);
					
			}
					/*if(rs.next())
					{
						while(rs.next())
						{
							Policyid=rs.getDouble(1);
							Policyname=rs.getString(2);
						}
					}*/
			/*	
			//2nd QUERY
			ps=con.prepareStatement(query2);
			
			//ps.setString(1, uname);
			//ps.setString(2, pass);
			
			ps.setString(1, policy);
			out.println(policy);
			rs=ps.executeQuery();	 
			*/
			
		
				{out.println("Policy Id: "+ Policyid+ " <br>"); 
				
				out.println("Policy Name: "+ Policyname + "<br>");
				
				//out.println("Policy Tenure: "+ Policyten+ " <br>");
				
				//out.println("Policy Premium: "+ Policyprem+  "<br>");
				
				//out.println("Nominee: " +nominee+  "<br>");
				
				//out.println("Sum assured: " + (sum assured) + "<br>");
				
				//out.println("Policy Expiry Date: " + expdate +  "<br>");
				}
				ps.close();
				con.close();
				rs.close();
			}
		
				
				catch(Exception e){
				PrintWriter out= response.getWriter();	
	
				out.println(e);
					
				}
		
		
	}

}

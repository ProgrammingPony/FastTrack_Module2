package org.fasttrack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DropListQuery
 */
@WebServlet("/DropListQuery")
public class DropListQuery extends HttpServlet {
	Connection con=null;
	ResultSet rs;
	PreparedStatement ps=null;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList resultset= new ArrayList();
		response.setContentType("text/html");
		//HttpSession hsession=request.getSession();
		//hsession.setAttribute("Pageuser", request.getParameter("user"));
		
		
		try{
						
					
					String uname;
					String pass;
					double Policyid=0;
					String Policyname="";
					String Policyten;
					String Policyprem;
					String nominee;
					
					PrintWriter out= response.getWriter();
					out= response.getWriter();
		
					String query="select policyname from policy";
					String Pass="tcs12345";
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user="system";
					response.setContentType("text/html");
					
					Class.forName("oracle.jdbc.OracleDriver");

					con=DriverManager.getConnection(url,user,Pass);
				
					ps=con.prepareStatement(query);
				
					//ps.setString(1, uname);
					//ps.setString(2, pass);
					
					
					rs=ps.executeQuery(query);
					
					while(rs.next()){
						
						resultset.add(rs.getString(1));
						
								
						}
						request.setAttribute("DL", resultset);
						//hsession.setAttribute("DL", resultset);
						
						/*for(int i=0; i<resultset.size(); i++)
						{
							if(resultset.get(i)!=null)
							out.println("<br>"+resultset.get(i));

						}
						*/
	}
		

		catch(Exception e){
		PrintWriter out= response.getWriter();	
		out.println("Your certificate can be seen above");
		out.println(e);
			
		}
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/PolicyCertificate.jsp");
		rd.forward(request, response);		
	}

}

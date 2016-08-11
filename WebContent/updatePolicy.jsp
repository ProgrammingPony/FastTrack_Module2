<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ page import="java.util.ArrayList" %>

   <%@ page import="org.fasttrack.db.module2.UpdatePolicyMethods" %>
<%
	//if ( (session == null) || (session.getAttribute("id") == null) || !((String)session.getAttribute("role")).equals("admin") ){ 
		//response.sendRedirect("main.jsp");
	//}

	if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("submit1") != null) {
    	UpdatePolicyMethods.displayPolicy(request, response);
    }

	else if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("submit") != null) {
    	UpdatePolicyMethods.updatePolicy(request, response);
    }
		%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<head>
		<title>Update Policy</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/login_registration_style.css">

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
		<script src="js/updatePolicy.js"></script>
	</head>

	<body>
		<div class="login-page">
			<div class="form">
				
				<form class="login-form" action = "updatePolicy.jsp" method = "post">
					<h1>Update Policy</h1>
					
						<%
							HttpSession hsession = request.getSession();
						System.out.println("errro message is "+hsession.getAttribute("error_msg2"));
							if (hsession.getAttribute("error_msg2") != null) {
						%>
						<h3 id="j-error-message" class="error-message">
							<%=(String) hsession.getAttribute("error_msg2")%>
						</h3>
						<%
							hsession.setAttribute("error_msg2", null);
						}
						%>

					
					<input id="j-username" type="text" placeholder="Policy ID" name="policyId" value="<%=hsession.getAttribute("policy_ID2") == null ? "" : (String) hsession.getAttribute("policy_ID2")%>" required>
					<% hsession.setAttribute("policy_ID2", null);%>
					<button name="submit1" id="confirmUpdate1">Submit</button>
				</form>
				
				<br>
				
				<%
				System.out.println(hsession.getAttribute("policy_name2"));
				System.out.println(hsession.getAttribute("policy_nom2"));
				System.out.println(hsession.getAttribute("policy_sumMin2"));
				if (hsession.getAttribute("policy_name2") != null
						&& hsession.getAttribute("policy_nom2") != null
						&& hsession.getAttribute("policy_sumMax2") != null
						&& hsession.getAttribute("policy_sumMin2") != null
						&& hsession.getAttribute("policy_prereq2") != null
						&& hsession.getAttribute("policy_isActive2") != null
						) {
			%>
			<div id="displayUpdatePolicy">
				<p id="close_popup" onclick="cancelDeactivate()">X</p>
				<br> <br>
				<form action="updatePolicy.jsp" method="post">
					Policy ID: <input type="text" name="policy_id2" id="policy_id2"
						value=<%=String.valueOf(hsession.getAttribute("policy_id2"))%>
						readonly> <br> <br> Policy is Active: <input
						type="text" name="policy_isActive2" id="policy_isActive2"
						value=<%=(String) hsession.getAttribute("policy_isActive2")%>
						readonly> <br> <br> Policy Name: <input
						type="text" name="policy_name2" id="policy_name2"
						value=<%=(String) hsession.getAttribute("policy_name2")%>> <br>
					<br> Policy Nominee: <input type="text" name="policy_nom2"
						id="policy_nom2"
						value=<%=String.valueOf(hsession.getAttribute("policy_nom2"))%>>
					<br> <br> Policy Minimum Sum: <input type="text"
						name="policy_sumMin2" id="policy_sumMin2"
						value=<%=String.valueOf(hsession
								.getAttribute("policy_sumMin2"))%>>
					<br> <br> Policy Maximum Sum: <input type="text"
						name="policy_sumMax2" id="policy_sumMax2"
						value=<%=String.valueOf(hsession
								.getAttribute("policy_sumMax2"))%>>
					<br> <br> Policy Prerequisite: <input type="text"
						name="policy_prereq2" id="policy_prereq2"
						value=<%=(String) hsession.getAttribute("policy_prereq2")%>>
					<br> <br>Tenure: <br>
					<%
						if (hsession.getAttribute("tenure_1yr2") != null) {
					%>
							<input type="checkbox" name="tenure1" value="tenure1" checked>1 year<br>
							<%
								} else {
							%>
							<input type="checkbox" name="tenure1" value="tenure1">1 year<br>
							<%
								}
		
									if (hsession.getAttribute("tenure_2yr2") != null) {
							%>
							<input type="checkbox" name="tenure2" value="tenure2" checked>2 years<br>
							<%
								} else {
							%>
							<input type="checkbox" name="tenure2" value="tenure2">2 years<br>
							<%
								}
		
									if (hsession.getAttribute("tenure_3yr2") != null) {
							%>
							<input type="checkbox" name="tenure3" value="tenure3" checked>3 years<br>
							<%
								} else {
							%>
							<input type="checkbox" name="tenure3" value="tenure3">3 years<br>
							<%
								}
		
									if (hsession.getAttribute("tenure_4yr2") != null) {
							%>
							<input type="checkbox" name="tenure4" value="tenure4" checked>4 years<br>
							<%
								} else {
							%>
							<input type="checkbox" name="tenure4" value="tenure4">4 years<br>
							<%
								}
		
									if (hsession.getAttribute("tenure_5yr2") != null) {
							%>
							<input type="checkbox" name="tenure5" value="tenure5" checked>5 years<br>
							<%
								} else {
							%>
							<input type="checkbox" name="tenure5" value="tenure5">5 years<br>
							<%
								}
		
									if (hsession.getAttribute("tenure_6yr2") != null) {
							%>
							<input type="checkbox" name="tenure6" value="tenure6" checked>6 years<br>
							<%
								} else {
							%>
							<input type="checkbox" name="tenure6" value="tenure6">6 years<br>
							<%
								}
							%>
					<button class="button" name="submit" id="confirmUpdate">Submit</button>
				</form>
		
			</div>
			<%
				hsession.setAttribute("policy_id2", null);
					hsession.setAttribute("policy_isActive2", null);
					hsession.setAttribute("policy_name2", null);
					hsession.setAttribute("policy_nom2", null);
					hsession.setAttribute("policy_sumMin2", null);
					hsession.setAttribute("policy_sumMax2", null);
					hsession.setAttribute("policy_prereq2", null);
					hsession.setAttribute("tenure_1yr2", null);
					hsession.setAttribute("tenure_2yr2", null);
					hsession.setAttribute("tenure_3yr2", null);
					hsession.setAttribute("tenure_4yr2", null);
					hsession.setAttribute("tenure_5yr2", null);
					hsession.setAttribute("tenure_6yr2", null);
				}
			%>
				
				

				<button><a href="main.jsp">Home</a></button>

				
			</div>
	</body>
	
	</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


    <%@ page import="org.fasttrack.db.module2.DeactivatePolicyMethods" %>
<%
	//if ( (session == null) || (session.getAttribute("id") == null) || !((String)session.getAttribute("role")).equals("admin") ){ 
		//response.sendRedirect("main.jsp");
	//}

	if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("submit") != null) {
    	DeactivatePolicyMethods.deactivatePolicy(request, response);
    	System.out.println("submit is "+request.getParameter("submit"));
    }
		%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<head>
		<title>Deactivate Policy</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/login_registration_style.css">

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
	</head>

	<body>
		<div class="login-page">
			<div class="form">
				
				<form class="login-form" action = "deactivatePolicy.jsp" method = "post">
					<h1>Deactivate Policy</h1>
					
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
					<button name="submit" id="confirmDeactivate">Submit</button>
				</form>
				
				<br>

				<button><a href="main.jsp">Home</a></button>

				
			</div>
	</body>
	
	</html>
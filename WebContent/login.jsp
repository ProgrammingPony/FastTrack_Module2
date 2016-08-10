<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% if ( (session != null) && (session.getAttribute("username") != null) ) response.sendRedirect("index.jsp"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>FastTrack Login</title>
	</head>
	<body>
		
		<% String username = request.getAttribute("id") == null ? "" : (String) request.getAttribute("id");  %>
	
		<form method="post" action="Login">
			<label for="username">Username</label>
			<input type="text" name="username" value="<%= username %>"/>
			
			<br/>
			
			<label for="password">Password</label>
			<input type="password" name="password" value=""/>
			
			<% if ( request.getAttribute("login-error") != null  ) { %>
				<div style="color:red"><%= (String) request.getAttribute("login-error") %></div>			
			<% } %>
			
			<input type="submit" value="Login"/>
		</form>
	</body>
</html>
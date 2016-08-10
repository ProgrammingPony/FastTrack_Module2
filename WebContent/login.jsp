
    <% if ( (session != null) && (session.getAttribute("username") != null) ) response.sendRedirect("index1.jsp"); %>
    
    <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		
		<title>FastTrack Login</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/login_registration_style.css">

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
		
	</head>
	<body>
		<div class="login-page">
		<div class="form">
		
			<% String username = request.getAttribute("id") == null ? "" : (String) request.getAttribute("id");  %>
		
			<form class = "login-form" method="post" action="Login">
				<h1>Login</h1>
	
				<input type="text" name="username" value="<%= username %>" placeholder = "Username"/>
				
				<br/>
				<input type="password" name="password" value="" placeholder="Password"/> <br>
				
				<% if ( request.getAttribute("login-error") != null  ) { %>
					<div style="color:red"><%= (String) request.getAttribute("login-error") %></div>			
				<% } %>
				
				<input type="submit" value="Login"/>
			</form>
			</div>
		</div>
	</body>
</html>
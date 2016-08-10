<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<style>
		.ft-left-menu-header {
			
		}
		
		.ft-left-link:link {
			font-weight:600;
		}
		.ft-left-link:link {
			color:purple;
		}
		.ft-left-link:hover {
			color:purple;
		}
		.ft-left-link:visited {
			color:purple;
		}
		
		</style>
	</head>
	
	<body>
		<div class="container-fluid" style="margin-top:2%;">
			<!-- Top Menu -->
			<div class="row">
				<div class="col-md-12" >
					<div class="jumbotron" style="margin-top:2%">
						<p>Fast Track</p>
					</div>					
				</div>
			</div>
		</div>
	
		<div class="container" style="margin-top:6px;">
			<div class="row">
			
				
				<div class="col-md-2" style="background:#ccccff; border-radius:5px;" >
					<!-- Logged in user data -->
					<% if (session != null && session.getAttribute("username") != null) { %>
						<h2 class="ft-left-menu-header">Hello</h2>
						<p><b>Username:</b> <%= (String)session.getAttribute("username") %></p>
						<p><b>Role:</b> <%= (String)session.getAttribute("role") %></p>
					<% } %>
					
					<!-- Vertical Menu on left -->
					<h2 class="ft-left-menu-header">Menu</h2>
				
					<ul class="nav nav-pills" style="border-radius:5px;">
						<% if (session.getAttribute("username") == null) { %>
							<li><a class="ft-left-link" href="login.jsp">Login</a></li>
						<% } else { %>
							<li><a class="ft-left-link" href="Logout">Logout</a></li>
						<% } %>
					</ul>					
				</div>
				
				<!-- Content Body -->
				<div class="col-md-10">
				
				</div>
				<!-- End Content Body -->
			</div>
		</div>
	</body>
</html>
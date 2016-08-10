<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Policy Registration - Admin</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/login_registration_style.css">

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
	</head>

	<body>
		<div class="login-page">
			<div class="form">
				<form class="login-form" action = "RegisterServlet" method = "get">
					<h1>Policy Registration</h1>
					<% String name = (String) request.getAttribute("error"); %>
					<% if ( name != null && !name.isEmpty() ) { %>
					<h3 id="j-error-message" class="error-message">Invalid Input <br><%= request.getAttribute("error")%></h3>
					<% } %>
				
					<input type = "text" name = "name" placeholder="Policy Name" value="<%= request.getAttribute("name") == null ? "" : (String) request.getAttribute("name") %>"></input><br>
					<input type = "text" name = "numNominee" placeholder= "Minimum Number of Nominee" value = "<%= request.getAttribute("numNominee") == null ? "" : (String) request.getAttribute("numNominee") %>" ></input><br>
					<label>Pre-Requisites</label><br>
					<textarea rows="10" cols="25" name = "prereq" value = "<%= request.getAttribute("prereq") == null ? "" : (String) request.getAttribute("prereq") %>"></textarea>
					<p><b>Tenure</b></p>
					<input type="checkbox" name="tenure1" value="1year">1 year<br>
					<input type="checkbox" name="tenure2" value="2year">2 year<br>
					<input type="checkbox" name="tenure3" value="3year">3 year<br>
					<input type="checkbox" name="tenure4" value="4year">4 year<br>
					<input type="checkbox" name="tenure5" value="5year">5 year<br>
					<input type="checkbox" name="tenure6" value="6year" checked>6 year<br>
					<input type = "text" name = "minSum" placeholder= "Minimum Sum Assured" value = "<%= request.getAttribute("minSum") == null ? "" : (String) request.getAttribute("minSum") %>"></input><br>
					<input type = "text" name = "maxSum" placeholder = "Maximum Sum Assured" value = "<%= request.getAttribute("maxSum") == null ? "" : (String) request.getAttribute("maxSum") %>"></input><br>
					<input type = "submit" value = "Submit"></input>
				</form>
			</div>
		</div>
	</body>

</html>




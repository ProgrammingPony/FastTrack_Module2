<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Manager Page</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/main_style.css">
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>

		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body>
		<div id="banner"></div>
		<div id="topBar">
			<ul>
				<li><a class="active" href="#home">Home</a></li>
				<li><a href="#news">News</a></li>
				<li><a href="#contact">Contact</a></li>
				<li><a href="#about">About</a></li>
			</ul>
		</div>
		<div id="body">
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand">
						<img id="logo" src="./img/logo.png">
						<p>Fasttrack Policy</p>
						<% if (session.getAttribute("id") == null) { %>
							<li><a href="login.jsp">Login</a></li>
						<% } else { %>
					</li>
					<li>
						<a href="#">Update Policy</a>
					</li>
					<li>
						<a href="#">Deactivate policy</a>
					</li>
					<li>
						<a href="#">Delete Policy</a>
					</li>
					<li>
						<a href="index.jsp">Register New Policy</a>
					</li>
					<li>
						<a href="Logout">Logout</a>
					</li>
					<%} %>
				</ul>
			</div>
			<div id="main"></div>
		</div>
		<footer>
		</footer>
	</body>

	<script>
		$(document).ready(function() {
		  $(window).scroll(function () {
		    if ($(window).scrollTop() > 352) {
		      $('#topBar').addClass('topBar-fixed');
		      $('#sidebar-wrapper').addClass('sidebar-fixed');
		    }
		    if ($(window).scrollTop() < 353) {
		      $('#topBar').removeClass('topBar-fixed');
		      $('#sidebar-wrapper').removeClass('sidebar-fixed');
		    }
		  });
		});

	</script>
</html>

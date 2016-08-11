<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,org.fasttrack.db.DBHelper,org.fasttrack.db.module2.DBMethods" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.


 if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("submit") != null) {
 	boolean success = DBMethods.updateNominee (request);    
 	
 	if (success) {
 		response.sendRedirect("updateNominee.jsp");
 	}
 }


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fasttrack Policy - Module 2</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/main_style.css">
		<link href="css/bootstrap.min.css" rel="stylesheet">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>  
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
  <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"> </script>

</head>
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
						
					</li>
					
					<% if(session.getAttribute("role") == "customer"){ %>
					<li>
						<a href="MyPolicies">My Policies</a>
					</li>
					<li>
						<a href="updateNominee.jsp">Update Nominee</a>
					</li>
					<li>
						<a href="PolicyCertificate.jsp">Generate Certificate</a>
					</li>
					<%} else if(session.getAttribute("role") == "manager"){%>
					<li>
						<a href="updatePolicy.jsp">Update Policy</a>
					</li>
					<li>
						<a href="deactivatePolicy.jsp">Deactivate policy</a>
					</li>
					<li>
						<a href="PolicyCertificate.jsp">Generate Certificate</a>
					</li>
					<li>
						<a href="search-policy-by-agent.xhtml">Policies of Agent Customers</a>
					</li>
					<li>
						<a href="#">Delete Policy</a>
					</li>
					
					<%} else if(session.getAttribute("role") == "admin"){%>
					<li>
						<a href="index.jsp">Register New Policy</a>
					</li>					
					<li>
						<a href="search-policy-by-agent.xhtml">Policies of Agent Customers</a>
					</li>
					<%}%>
					
					<% if (session.getAttribute("id") == null) { %>
							<li><a href="login.jsp">Login</a></li>
					<% } else { %>					
					<li>
						<a href="logout.jsp">Logout</a>
					</li>
					<% } %>
				</ul>
			</div>
			<div id="main"><% 
Class.forName("oracle.jdbc.OracleDriver");

Connection connection = DriverManager.getConnection(
		DBHelper.getDbUrl(),
		DBHelper.getUser(),
		DBHelper.getPass()
		);


Statement statement = connection.createStatement();
ResultSet rs = statement.executeQuery("select * from nominee where customerId = 0");



%>


<div class="container">
<table class="table table-striped">
<thead>
<tr>
<th>Nominee ID</th>
<th>Policy ID </th>
<th>Nominee Name</th>
<th>Nominee Relationship</th>
<th>Nominee Date of birth</th>
</tr>
</thead>
<tbody>
<%! int i=0; %>
<%while(rs.next()) {%>
<tr>
<td id="nomID-<%=i%>"><%=rs.getInt(1) %></td>
<td><%= rs.getInt(3) %></td>
<td><%= rs.getString(4)%></td>
<td><%= rs.getString(5) %></td>
<td><%= rs.getString(6) %></td>
<td> <button id="<%= i %>" class="update-button" onclick="update()">update</button></td>


</tr>

<% i++; %>
<% } %>
</tbody>
</table>
</div>
<p id="msg"><p>
<div class="container center_div">
<div id="formdiv">
<form id="update-form" action="updateNomineeServlet" method="POST" class="form-horizontal" role="form" data-toggle="validator" style="display:none;">

<div class="form-group">
    <label class="control-label col-sm-2" id="nomineeIDshow"></label>
    <div class="col-sm-10">
     
    </div>
  </div>

<input id="nomineeID" type="hidden" name ="nomineeID">


 <div class="form-group">
    <label class="control-label col-sm-2" for="newNomineeName">New nominee name:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="newNomineeName" name="newNomineeName">
    </div>
  </div>

 <div class="form-group">
    <label class="control-label col-sm-2" for="newRelationToNominee">Relationship to nominee:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="newRelationToNominee" name="newRelationToNominee">
    </div>
  </div>
 <div class="form-group">
    <label class="control-label col-sm-2" for="newdob">Date of birth(DD-MM-YYYY):</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="newdob" name="newdob">
    </div>
  </div>
 <div class="form-group">
    <label class="control-label col-sm-2" for="newNomineePurpose">Purpose for changing nominee:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="newNomineePurpose" name="newNomineePurpose">
    </div>
  </div>
 <div class="form-group">
    <label class="control-label col-sm-2" for="IDProof">Upload new nominee personal ID proof:</label>
    <div class="col-sm-10">
      <input type="file" class="form-control" id="IDProof" name="IDProof">
    </div>
  </div>

 <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Update</button>
    </div>
  </div>

</form>

</div>
</div></div></div>
		
		<footer>
		</footer>

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

$(document).ready(function() {
  $('#update-form').bootstrapValidator({
  	icon:{
  		valid: 'glyphicon glyphicon-ok',
  		invalid:'glyphicon glyphicon-remove',
  		validatigin: 'glyphicon glyphicon-refresh'
  	},
  	fields: {
  		newNomineeName: {
  			validators:{
  				notEmpty:{
  					message:'Nominee name is required'
  				},
  				stringLength:{
  					min:2,
  					message: 'Name must be longer than 1 characters'
  				},
  				regexp:{
  					regexp: /^[a-zA-Z\s]+$/,
  					message: 'Name can only consist of letters'
  				}
  				
  			}
  		},
  		newRelationToNominee: {
  			validators:{
  				notEmpty:{
  					message: 'Relationship required'
  				},
  				stringLength:{
  					min:3,
  					message: 'Relationship must be longer than 2 characters'
  				},
  				regexp:{
  					regexp: /^[a-zA-Z]+$/,
  					message: 'Relationship can only consist of letters'
  				}
  				
  			}
  		},
  		newdob:{
  			validators:{
  				notEmpty:{
  					message: 'Date is required'
  				},
  			date:{
  				format:'DD-MM-YYYY',
  				message:'The value is not a valid date'
  			}
  			}
  		},
  		newNomineePurpose:{
  			validators:{
  				notEmpty:{
  					message:'Purpose for nomine change is required'
  				}
  			}
  		}
  		}
  	});
  });

$('.update-button').click( function() {
	$('#update-form').css('display', 'block');
	
	id = $(this).attr('id');
	var string  =$('#nomID-'+id).html();
	$('#nomineeID').val(string);
	$('#nomineeIDshow').text("Nominee Id: " +string);
	
});

</script>


</body>
</html>
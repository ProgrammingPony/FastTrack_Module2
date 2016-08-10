<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,org.fasttrack.db.DBHelper" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<style>
  table.center {
    margin-left:auto; 
    margin-right:auto;
    width:100%;
    text-align:center;
  }
  #update-form{
    margin:0 auto;
    width:80%;
      text-align: center;
  }
  #formdiv{
   margin-left: auto;
    margin-right: auto;
    width: 100%;
  }
</style>
</head>
<body>

<% 
Class.forName("oracle.jdbc.OracleDriver");

Connection connection = DriverManager.getConnection(
		DBHelper.getDbUrl(),
		DBHelper.getUser(),
		DBHelper.getPass()
		);


Statement statement = connection.createStatement();
ResultSet rs = statement.executeQuery("select * from nominee where customerId = 1234");



%>


<table class="center">
<tr>
<th>Nominee ID</th>
<th>Policy ID </th>
<th>Nominee Name</th>
<th>Nominee Relationship</th>
<th>Nominee Date of birth</th>
</tr>
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

</table>
<div id="formdiv">
<form id="update-form" action="updateNomineeServlet" method="POST" style="display:none;">

<p id="nomineeIDshow"></p>
<input id="nomineeID" type="hidden" name ="nomineeID">

New nominee name: <br>
<input id="newNomineeName" type="text" name="newNomineeName">
<br>
Relationship to nominee:<br>
<input id="newRelationToNominee" type="text" name="newRelationToNominee">
<br>
Date of birth (DD-MM-YYYY): <br>
<input id="newdob" type="text" name="newdob" data-validation="date" data-validation-format="dd-mm-yyyy">
<br>
Purpose for changing nominee: <br>
<input id="newNomineePurpose" type="text" name="newNomineePurpose">
<br>
Upload new nominee personal ID proof:<br>
<input id="IDProof" type="file" name="IDProof" accept="image/*">
<br>
<br>
<input type="submit" VALUE="UPDATE"></input>

</form>

</div>
<script>
$.validate({

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
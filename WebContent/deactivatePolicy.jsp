<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="formStyle.css">
<title>Deactivate Policy</title>
</head>
<body>

	<h1 align="center">Deactivate Policy</h1>

	<%
		HttpSession hsession = request.getSession();
		if (hsession.getAttribute("error_msg2") != null) {
	%>
	<div id="errorMsg">
		<%=(String) hsession.getAttribute("error_msg2")%></div>
	<%
		hsession.setAttribute("error_msg2", null);
		}
	%>


	<br>


	<div id="deactivatePolicy">
		<form action="deactivatePolicy" method="post">

			<input type="text" name="policyId" id="policyId"
				placeholder="Policy ID"
				value="<%=hsession.getAttribute("policy_ID2") == null ? ""
					: (String) hsession.getAttribute("policy_ID2")%>"
				required> <br> <br>
				<% hsession.setAttribute("policy_ID2", null);%>


			<button class="button" id="confirmDeactivate">Submit</button>

			<!--  <div class="popup" id="deactivatePopup"> 
			<p id="close_popup" onclick="cancelDeactivate()">X</p>
			<br> <br>
		
			<p style="align:center;">Are you sure you want to deactivate the policy?</p>
			<br>
			<button class="button" id="confirm" type="submit" value="Submit">Okay</button>
			
			
		</div>-->
		</form>

	</div>
</body>
</html>
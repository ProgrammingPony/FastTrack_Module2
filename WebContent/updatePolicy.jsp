<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
	%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/formStyle.css">
<script src="js/updatePolicy.js"></script>
<title>Update Policy</title>
</head>
<body>

	<h1 align="center">Update Policy</h1>

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


	<div id="updatePolicy">
		<form action="updatePolicyID" method="post">

			<input type="text" name="policyId" id="policyId"
				placeholder="Policy ID"
				value="<%=hsession.getAttribute("policy_ID2") == null ? ""
					: (String) hsession.getAttribute("policy_ID2")%>"
				required> <br> <br>
				<% hsession.setAttribute("policy_ID2", null);%>

			<button class="button" id="confirmUpdate1">Submit</button>
		</form>
	</div>
	<br>
	<br>

	<%
		if (hsession.getAttribute("policy_name2") != null
				&& hsession.getAttribute("policy_nom2") != null
				&& hsession.getAttribute("policy_sumMax2") != null
				&& hsession.getAttribute("policy_sumMin2") != null
				&& hsession.getAttribute("policy_prereq2") != null
				&& hsession.getAttribute("policy_isActive2") != null) {
	%>
	<div id="displayUpdatePolicy">
		<p id="close_popup" onclick="cancelDeactivate()">X</p>
		<br> <br>
		<form action="updatePolicy" method="post">
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
			<br> <br>
			
			<button class="button" id="confirmUpdate">Submit</button>
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

		}
	%>

</body>
</html>
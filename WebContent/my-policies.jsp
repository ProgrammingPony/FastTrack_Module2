<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList,java.util.TreeMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>My Policies</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	</head>
	
	<body>
		<form>
			
			<select id="policy-name-list">
				<%! ArrayList < TreeMap<String,Object> > list; %>
				
				<% if (( list = (ArrayList< TreeMap<String,Object> >) request.getAttribute("my-policy-list") ) != null) { %>
					<% for ( TreeMap<String,Object> map : list) { %>
					<option id="<%= map.get("id") %>" value="<%= map.get("id") %>"><%= map.get("name") %></option>
					<% } %>				
				<% } %>
			</select>
						
			<button id="view-details-button">View Policy Details</button>
		</form>
		
		<div id="policy-details">
			Policy Id: <span id="detail-id"></span>
			Policy Name: <span id="detail-name"></span>
			Tenure: <span id="detail-tenure"></span>
			Sum Assured: <span id="detail-sum-assured"></span>
			Premium: <span id="detail-premium"></span>
			Expiry: <span id="detail-expiry"></span>
		</div>
	</body>
</html>
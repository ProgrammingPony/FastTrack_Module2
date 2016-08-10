<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList,java.util.TreeMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>My Policies</title>
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
			
			<input type="text" name="selected-policy-name" style="visibility:hidden; display:none;"/>
			
			<input type="submit" name="View Policy Details"/>
		</form>
		
		<div id="policy-details">
			Policy Id:
			Policy Name:
			Tenure:
			Sum Assured:
			Premium: 
			Expiry:
		</div>
	</body>
</html>
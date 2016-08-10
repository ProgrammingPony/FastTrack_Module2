<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList,java.util.TreeMap" %>
    <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>My Policies</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		
		<%! ArrayList < TreeMap<String,Object> > list; %>
		<% if ( (request.getAttribute("my-policy-list") != null)  ) {
			list = (ArrayList< TreeMap<String,Object> >) request.getAttribute("my-policy-list");
		} %>
		<script>
			policyDetails = [];
				<% for ( TreeMap<String,Object> map : list ) { %>
				policyDetails[<%= (Integer) map.get("id") %>] = {
						id: <%= (Integer) map.get("id") %>,
						name: '<%= (String) map.get("name") %>',
						tenure: <%= (Integer) map.get("tenure") %>,
						premium: <%= (Integer) map.get("premium") %>,
						sumAssured: <%= (Integer) map.get("sumAssured") %>,
						expiry: '<%= (String) map.get("expiry") %>'
					};
				<% } %>

		</script>
	</head>
	
	<body>			
		<select id="policy-name-list">
			
			
			<% if (( list = (ArrayList< TreeMap<String,Object> >) request.getAttribute("my-policy-list") ) != null) { %>
				<% for ( TreeMap<String,Object> map : list) { %>
				<option id="<%= map.get("id") %>" value="<%= map.get("id") %>"><%= map.get("name") %></option>
				<% } %>				
			<% } %>
		</select>
			
			
						
		<button id="view-details-button">View Policy Details</button>
		
		<div id="policy-details" style="display:none;">
			<b>Policy Id:</b> <span id="detail-id"></span>
			<br>
			<b>Policy Name:</b> <span id="detail-name"></span>
			<br>
			<b>Tenure:</b> <span id="detail-tenure"></span>
			<br>
			<b>Sum Assured:</b> <span id="detail-sum-assured"></span>
			<br>
			<b>Premium:</b> <span id="detail-premium"></span>
			<br>
			<b>Expiry:</b> <span id="detail-expiry"></span>
			<br>
		</div>
		
		<script>
			$('#view-details-button').click(function(){
				
				var option = $('#policy-name-list').find(":selected").attr('id');
				
				$('#detail-id').html( policyDetails[option].id );
				$('#detail-name').html( policyDetails[option].name );
				$('#detail-tenure').html( policyDetails[option].tenure );
				$('#detail-sum-assured').html( policyDetails[option].sumAssured );
				$('#detail-expiry').html( policyDetails[option].expiry );
				$('#detail-premium').html( policyDetails[option].premium );
				
				$('#policy-details').css('display', 'block');
			});
		</script>
	</body>
</html>
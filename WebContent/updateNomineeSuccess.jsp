<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
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
</head>
<body>

<% 
Class.forName("oracle.jdbc.OracleDriver");

Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tcs12345");


Statement statement = connection.createStatement();
ResultSet rs = statement.executeQuery("select * from nominee where customerId = 1234");



%>


<table>
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


</tr>

<% i++; %>
<% } %>

</table>

<p> Nominee info successfully updated.<p>
<form action="updateNominee.jsp">
    <input type="submit" value="Update another nominee" />
</form>


</body>
</html>
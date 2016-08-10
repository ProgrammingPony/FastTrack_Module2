<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Policy Certificate</title>

</head>
<body>
<script type="text/javascript">
function policy(){
var e = document.getElementById("dl");
var strUser = e.options[e.selectedIndex].text;
document.getElementById("pselect").value=strUser;
}
</script>
<form action="DropListQuery" method="post">

<input type="submit" value="Update policy droplist">
</form>
<br>
<h1 >Policy Certificate</h1>

<%! ArrayList<String> options;%>

<%
options= request.getAttribute("DL") == null ? new ArrayList<String>() : (ArrayList) request.getAttribute("DL");
%>
<form action="policyCert" method="post" name="cert">
User: <input type="text" name="user">

   Customer Id: <input type="text" ><br>

Policy: <select name="dl" id="dl" onChange="policy()">
	<option>Please select a policy</option>
   <%for(int i=0; i<options.size();i++){%>
        <option><%out.print(options.get(i));%></option>
    <%}%>
</select>
<input type="text" name="pselect" id="pselect">



<br>
<input type="submit" value="View Certificate"> 
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="MyWebsite.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MVC Success</title>
</head>
<body>
<%
UserBean userobj=(UserBean)request.getAttribute("userLoginBean");  
out.print("Welcome, " + userobj.getName());
%>
</body>
</html>
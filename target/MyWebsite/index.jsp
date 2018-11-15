<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="MyWebsite.MsgBean"%>
<%@ page import="MyWebsite.SessionBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
MsgBean msgobj = new MsgBean();
if ( request.getAttribute("msgBean") == null ) {
	msgobj.setMessage("Please Enter Credentials");
} else {
	msgobj=(MsgBean)request.getAttribute("msgBean");
}

SessionBean sessionBean = new SessionBean();
session.setAttribute("sessionBean", sessionBean);

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Website</title>
    </head>
    <body bgcolor="white">
         <!--
     <c:redirect url="listrecipe.jsp"/>

    <c:set var="currentPage" scope="session" value="0"/>
    <c:set var="recordsPerPage" scope="session" value="10"/>
    <c:set var="filterValue" scope="session" value=""/>
    <c:set var="noOfPages" scope="session" value="0"/>
    <c:redirect url="listrecipe.jsp"/>

     	<form method="post" action="ReadRecipesServlet">		
     		<input type="hidden" name="currentPage" value="0">
     		<input type="hidden" name="recordsPerPage" value="10">
     		<input type="hidden" name="filterValue" value="pie">
            <table border="1px" align=center bgcolor="silver" >
            
                <thead>
                    <tr bgcolor="gray">
                        <th colspan="2">Login Page - version 2</th>
                    </tr>
                    <tr>
                        <th colspan="2"><% out.print(msgobj.getMessage()); %></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="uname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Login" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">New User <a href="register.jsp">Register Here</a></td>
                    </tr>
                </tbody>
            </table>
        </form>
-->
<h1> Index page</h1>
    </body>
</html>

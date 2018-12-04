<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ page isELIgnored="false" %> 
<%@ page import="MyWebsite.RecipeDetailBean"%>
<%
RecipeDetailBean recipeobj = new RecipeDetailBean();
recipeobj=(RecipeDetailBean)request.getAttribute("recipeDetaildBean");
pageContext.setAttribute("recipe", recipeobj);
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3> Edit Recipe</h3>
<c:out value="${recipe.Id}"/>
<c:out value="${recipe.Name}"/>
<c:out value="${recipe.yield}"/>
<c:out value="${recipe.yieldunit}"/>
<c:out value="${recipe.preptime}"/>
<c:out value="${recipe.cooktime}"/>
<c:out value="${recipe.directions}"/>
</body>
</html>
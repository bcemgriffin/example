<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ page isELIgnored="false" %> 
<%@ page import="MyWebsite.RecipeDetailBean"%>

<%
RecipeDetailBean recipeobj = new RecipeDetailBean();
recipeobj=(RecipeDetailBean)request.getAttribute("recipeDetailBean");
pageContext.setAttribute("recipe", recipeobj);
%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"> 
<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
    
<title>Edit Recipe</title>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>
<div class="grid">
    <div class="title">
        <div class="center">
        <h1>Online Recipe Book</h1>
        </div>
    </div>
    
    <div class="header"></div>

    <div class="sidebar">
        <a href="ReadRecipesServlet?recordsPerPage=10&currentPage=1&filterValue=">Recipes</a>
        <a href="#addrecipe">Add Recipe</a>
        <a class="active" href="#showrecipe">Show Recipe</a>
    </div>
    
    <div class="content">
    	<form method="post" action="ReadRecipesServlet">
			<table>
			    <tr>
			    	<th colspan=2>Recipe Details</th>
            	</tr>
				<tr>
					<td style="width:150px">Recipe Name</td>
					<td><c:out value="${recipe.getName()}"/></td>
				</tr>
				<tr>
					<td style="width:150px">Yield ${recipe.getYieldunit()}</td>
					<td><c:out value="${recipe.getYield()}"/></td>
				</tr>
				<tr>
					<td style="width:150px">Prep Time <i>(HH:MM)</i></td>
					<td><c:out value="${recipe.getPreptime()}"/></td>
				</tr>
				<tr>
					<td style="width:150px">Cook Time <i>(HH:MM)</i></td>
					<td><c:out value="${recipe.getCooktime()}"/></td>
				</tr>
				<tr>
					<td colspan=2>Directions</td>
				</tr>
				<tr>
					<td colspan=2>${recipe.getDirections()}</td>
				</tr>
				<tr><td colspan=2><button type="submt">Close</button></td></tr>
			</table>
		</form>
	</div>
	
	<div class="footer">
        <div class="center">				
		</div>
	</div>
</div>


</body>
</html>
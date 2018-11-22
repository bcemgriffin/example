<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ page isELIgnored="false" %> 
<%@ page import="beans.RecipeDetailBean"%>
<%@ page import="beans.IngredientBean"%>
<%@ page import="java.util.ArrayList" %>

<%
RecipeDetailBean recipeobj = new RecipeDetailBean();
recipeobj=(RecipeDetailBean)request.getAttribute("recipeDetailBean");
pageContext.setAttribute("recipe", recipeobj);

ArrayList<IngredientBean> ingredientlistobj = recipeobj.getIngredientlist();
pageContext.setAttribute("ingredientlist", ingredientlistobj);
%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"> 
<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
    
<title>Recipe Details</title>
<style>

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
					<td colspan=2><h2><c:out value="${recipe.getName()}"/></h2></td>
				</tr>
				<tr>
					<td style="border: none;">Yields </td>
					<td style="border: none;">${recipe.getYield()} ${recipe.getYieldunit()}</td>
				</tr>
				<tr>
					<td style="border: none;">Prep Time <i style="font-size: 10;">(HH:MM)</i></td>
					<td style="border: none;"><c:out value="${recipe.getPreptime()}"/></td>
				</tr>
				<tr>
					<td style="border: none;">Cook Time <i style="font-size: 10;">(HH:MM)</i></td>
					<td style="border: none;"><c:out value="${recipe.getCooktime()}"/></td>
				</tr>
				<tr>
					<td colspan=2><h3>Ingredients</h3></td>
				</tr>
				<c:forEach var="ingredient" items="${ingredientlist}">
					<tr style="border: none;">
						
				 		<td style="border: none;">
				 			${ingredient.getIngredientAmtString1()} ${ingredient.getIngredientAmtString2()} ${ingredient.getIngredientUnit()}
						</td>
						<td style="border: none;">${ingredient.getIngredientName()}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan=2 style="border: none;"><h3>Directions</h3></td>
				</tr>
				<tr>
					<td colspan=2 style="border: none;">${recipe.getDirections()}</td>
				</tr>
				<tr><td colspan=2 style="border: none;"><button type="submt">Close</button></td></tr>
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
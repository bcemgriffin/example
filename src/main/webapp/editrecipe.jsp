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
<div class="grid3">
    <div class="title">
        <div class="center">
        <h1>Online Recipe Book</h1>
        </div>
    </div>
    
    <div class="header"></div>

    <div class="sidebar">
        <a href="ReadRecipesServlet?recordsPerPage=10&currentPage=1&filterValue=">Recipes</a>
        <a href="#addrecipe">Add Recipe</a>
        <a class="active" href="#editrecipe">Edit Recipe</a>
    </div>
    
    
   	 	<div class="content">
   	 	<form method="post" action="UpdateRecipeDetailsServlet">
   	 		<table style="border: 1px solid black; width: 100%;">
   	 			<tr style="border: none;">
   	 				<td style="width:50%; border: none;"><h2>Recipe Details</h2></td>
   	 				<td style="width:50%; border: none;"><h2>Ingredients</h2></td>
   	 			</tr>
   	 			<tr style="border: none;">
   	 				<td style="width:50%; border: none;">
   	 				
  						<table style="border: none; width: 100%; height:100%">
							<tr style="border: none;">
								<td style="width:150px; border: none;"">Recipe Name</td>
								<td style="border: none;"><input type="text" name="recipeName" value="${recipe.getName()}"/></td>
							</tr>
							<tr>
								<td style="width:150px; border: none;">Yield</td>
								<td style="border: none;"><input type="text" name="recipeYield" value="${recipe.getYield()}"/></td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Yield Unit</td>
								<td style="border: none;"><input input="text" name="recipeYieldunit" placeholder="dozen, servings, ..." value="${recipe.getYieldunit()}"/></td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Prep Time <i>(HH:MM)</i></td>
								<td style="border: none;"><input type="text" name="recipePreptime" pattern="[0-9][0-9]:[0-9][0-9]" placeholder="HH:MM" value="${recipe.getPreptime()}"/></td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Cook Time <i>(HH:MM)</i></td>
								<td style="border: none;"><input type="text" name="recipeCooktime" pattern="[0-9][0-9]:[0-9][0-9]" placeholder="HH:MM" value="${recipe.getCooktime()}"/></td>
							</tr>
						</table>
			
					</td>
					<td style="width:50%; border: none;">
						<table style="border: none; width: 100%;">
							<td style="border: none;"></td>
							<td style="border: none;">Amt</td>
							<td style="border: none;">Unit</td>
							<td style="border: none;">Ingredient</td>
						<c:forEach var="index" begin="1" end="10">
							<tr style="border: none;">
								<td style="border: none;"><c:out value="${index}."/></td>
								<td style="border: none;"><input input="text" name="ingredientAmt${index}" size="3"/></td>
								<td style="border: none;"><input input="text" name="ingredientUnit${index}" size="5"/></td>
								<td style="border: none;"><input input="text" name="ingredientName${index}" placeholder="Enter Ingredient ${index}" size="25"/></td>
							</tr>
						</c:forEach>
						</table>
					</td>
			</table>
		
			<h2>Directions</h2>
			<textarea id="directions" name="recipeDirections">${recipe.getDirections()}</textarea>
			<button type="submit">Save</button>
							
			<script>
				CKEDITOR.replace( 'directions' );
			</script>
			</form>
		</div>

	
	
	<div class="footer">
        <div class="center">
        Footer				
		</div>
	</div>
</div>


</body>
</html>
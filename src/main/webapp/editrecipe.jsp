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
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
        jQuery(function(){
        	jQuery('a.add-ingredient').click(function(event){
            event.preventDefault();

            var newRow = jQuery('<tr style="border: none;"><td style="border: none;"><input style="width:50px; text-align: right;" type="number" name="ingredientAmt1" min="0" max="500" value="0"/><select style="width:50px; padding:2px" id="amt" name="ingredientAmt2"><option value=""></option><option value="1/8">1/8</option><option value="1/4">1/4</option><option value="1/3">1/3</option><option value="1/2">1/2</option><option value="2/3">2/3</option><option value="3/4">3/4</option></select></td><td style="border: none;" ><select style="width:80px"id="unit" name="ingredientUnit"><option value="pinch">pinch</option><option value="tsp">tsp</option><option value="tbls">tbls</option><option value="cup">cup</option><option value="oz">ounce</option><option value="lbs">lbs</option></select></td><td style="border: none;"><input type="text" name="ingredientName" placeholder="Enter Ingredient ${index}" size="25"/></td></tr>');
             jQuery('table.ingredient-list').append(newRow);

        });
        });
</script>  	
  
<title>Edit Recipe</title>

<style>
table, th, td {
    border: 1px solid black;
}

select {
    width: 100%;
    padding: 1px 1px;
    border: 1px solid black;
    border-radius: 1px;
}
</style>
</head>

<body>
<div class="grid3">
    <div class="title">
       	<h1>Online Recipe Book</h1>
    </div>
    
    <div class="header"></div>

    <div class="sidebar">
        <a href="ReadRecipesServlet?recordsPerPage=10&currentPage=1&filterValue=">Recipes</a>
        <a href="addrecipe.jsp">Add Recipe</a>
        <a class="active" href="#editrecipe">Edit Recipe</a>
    </div>
    
    
   	<div class="content">
  	 	<form method="post" action="UpdateRecipeDetailsServlet" enctype = "multipart/form-data">
   	 		<table style="border: 1px solid black; width: 100%;">
   	 			<tr style="border: none;">
   	 				<th style="width:35%; border: none;"><h2>Recipe Details</h2></th>
   	 				<th style="width:65%; border: none;"><h2>Ingredients    <a href="#" title="" class="add-ingredient"><i style="font-size:12; font-weight: normal;">Add ingredient row</i></a></h2></th>
   	 			</tr>
   	 			<tr style="border: none;">
   	 				<td  valign="top" style="width:50%; border: none;">
	  					<table style="border: none; width: 100%; height:100%;">
							<tr style="border: none;">
								<td style="width:150px; border: none;">Recipe Name</td>
								<td style="border: none;"><input type="text" name="recipeName" value="${recipe.getName()}"/></td>
							</tr>
							<tr>
								<td style="width:150px; border: none;">Yield</td>
								<td style="border: none;"><input type="text" name="recipeYield" value="${recipe.getYield()}"/></td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Yield Unit</td>
								<td style="border: none;"><input type="text" name="recipeYieldunit" placeholder="dozen, servings, ..." value="${recipe.getYieldunit()}"/></td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Prep Time <i style="font-size:12;">(HH:MM)</i></td>
								<td style="border: none;"><input type="text" name="recipePreptime" pattern="[0-9][0-9]:[0-9][0-9]" placeholder="HH:MM" value="${recipe.getPreptime()}"/></td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Cook Time <i style="font-size:12;">(HH:MM)</i></td>
								<td style="border: none;">
									<input type="text" name="recipeCooktime" pattern="[0-9][0-9]:[0-9][0-9]" placeholder="HH:MM" value="${recipe.getCooktime()}"/>
								</td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Current Image</td>
								<td style="border: none;">${recipe.getPhotoName()}</td>
							</tr>
							<tr style="border: none;">
								<td style="width:150px; border: none;">Pick New Image <i style="font-size:12;">(opt)</i></td>
								<td style="border: none;"><input type = "file" name = "recipePhoto"/></td>
							</tr>
						</table>
					</td>
					<td style="width:50%; border: none;">

						<div class="contentvscroll">
							<table style="border: none; width: 100%;" class="ingredient-list">
								<tr style="border: none;">
									<th style="border: none;">Amount</th>
									<th style="border: none;">Unit</th>
									<th style="border: none;">Ingredient</th>
								</tr>
						
								<c:forEach var="ingredient" items="${ingredientlist}">
									<tr style="border: none;">
								 		<td style="border: none;">
								 			<input style="width:50px; text-align: right;" type="number" name="ingredientAmt1" min="0" max="500" value="${ingredient.getIngredientAmtString1()}"/><input style="width:50px;" type="text" name="ingredientAmt2" pattern="[0-9]/[0-9]" value="${ingredient.getIngredientAmtString2()}"/>
										</td>
										<td style="border: none;">
											<input style="width:80px" type="text" name="ingredientUnit" value="${ingredient.getIngredientUnit()}"/>
										</td>	
										<td style="border: none;">
											<input type="text" name="ingredientName" placeholder="Enter Ingredient ${index}" size="25" value="${ingredient.getIngredientName()}"/>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</table>
		
			<h2>Directions</h2>
			<textarea id="directions" name="recipeDirections">${recipe.getDirections()}</textarea>
			<script>
				CKEDITOR.replace( 'directions' );
			</script>
			<button type="submit">Save</button>
		</form>
	</div>

	<div class="footer">
	</div>
</div>


</body>
</html>
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
      <style type = "text/css">
         #spinner-text,#spinner-nbr input {width: 50px; height:20px}
      </style>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"> 
<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="/resources/demos/external/jquery-mousewheel/jquery.mousewheel.js"></script>
  <script>
  $.widget("ui.formatSpinner", $.ui.spinner, {
	    options: {
	    },
	    _parse: function (value) {
	        if (typeof value === "string") {                
	            return this.options.values.indexOf(value);
	        }
	        return value;            
	    },
	    _format: function (value) {
	        //wrap around
	        if (value < 0) {
	            value = this.options.values.length-1;
	        }
	        if (value > this.options.values.length-1) {
	            value = 0;
	        }
	        var format = this.options.values[value];
	        return format;
	    },          
	}); 

	var arrText = ["", "1/8", "1/4", "1/3", "1/2", "2/3", "3/4"];

	$(function() {
	    $("#spinner-text").formatSpinner({
	        values: arrText,
	        spin: function( event, ui ) {
	           $( "#spinner-value" ).text(ui.value);
	        }
	    });

	    $("#spinner-nbr").spinner({ 
	        min: 0,
	        step: 1,
	        spin: function( event, ui ) {
		    	$( "#spinner-value-nbr" ).text(ui.value);
		    }
	    });
	});
	
	$('#select-unit-list').selectize({
	})
	
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
   	 	<form method="post" action="UpdateRecipeDetailsServlet" enctype = "multipart/form-data">
   	 		<table style="border: 1px solid black; width: 100%;">
   	 			<tr style="border: none;">
   	 				<td style="width:40%; border: none;"><h2>Recipe Details</h2></td>
   	 				<td style="width:60%; border: none;"><h2>Ingredients</h2></td>
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
								<td style="width:150px; border: none;">Photo</td>
								<td style="border: none;"><input type = "file" name = "recipePhoto" size = "50" /></td>
							</tr>
						</table>
					</td>
					<td style="width:50%; border: none;">
					  <div class="contentvscroll">
						<table style="border: none; width: 100%;" >
						<tr style="border: none;">
							<td style="border: none;">Amt</td>
							<td style="border: none;">Unit</td>
							<td style="border: none;">Ingredient</td>
						</tr>
						
						<c:forEach var="ingredient" items="${ingredientlist}">
							
							<tr style="border: none;">
						 		<td style="border: none;">
						 			<input style="width:50px;" type="number" name="ingredientAmt1" min="1" max="500" value="${ingredient.getIngredientAmtString1()}"/>
									<input style="width:50px;" type="text" name="ingredientAmt2" pattern="[0-9]/[0-9]" value="${ingredient.getIngredientAmtString2()}"/>
								</td>
								<td style="border: none;">
									<input style="width:80px" type="text" name="ingredientUnit" value="${ingredient.getIngredientUnit()}"/>
								</td>
								<td style="border: none;">
									<input type="text" name="ingredientName" placeholder="Enter Ingredient ${index}" size="25" value="${ingredient.getIngredientName()}"/></td>
							</tr>
						</c:forEach>
						<c:forEach var="index" begin="${ingredientlist.size()+1}" end="20">
							<tr style="border: none;">
						 		<td style="border: none;">
						 			<input style="width:50px;" type="number" name="ingredientAmt1" min="0" max="500" value="0"/>
									<select style="width:50px;" id="amt" name="ingredientAmt2">
										<option value=""></option>
										<option value="1/8">1/8</option>
										<option value="1/4">1/4</option>
										<option value="1/3">1/3</option>
										<option value="1/2">1/2</option>
										<option value="2/3">2/3</option>
										<option value="3/4">3/4</option>
									</select>
								</td>
								<td style="border: none;" >
									<select style="width:80px"id="unit" name="ingredientUnit">
										<option value="pinch">pinch</option>
										<option value="tsp">tsp</option>
										<option value="tbls">tbls</option>
										<option value="cup">cup</option>
										<option value="oz">ounce</option>
										<option value="lbs">lbs</option>
									</select>
								</td>
								
								<td style="border: none;"><input type="text" name="ingredientName" placeholder="Enter Ingredient ${index}" size="25"/></td>
							</tr>
						</c:forEach>
						
						</table>
					  </div>
					</td>
				</tr>
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
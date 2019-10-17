<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ page isELIgnored="false" %> 
<%@ page import="beans.RecipeDetailBean"%>
<%@ page import="beans.IngredientBean"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.SessionBean"%>

<%
SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
pageContext.setAttribute("sessionbean", sessionBean);

RecipeDetailBean recipeobj = new RecipeDetailBean();
recipeobj=(RecipeDetailBean)request.getAttribute("recipeDetailBean");
pageContext.setAttribute("recipe", recipeobj);

ArrayList<IngredientBean> ingredientlistobj = recipeobj.getIngredientlist();
pageContext.setAttribute("ingredientlist", ingredientlistobj);

String[] fractions = {"", "1/8", "1/4", "1/3", "1/2", "2/3", "3/4"};
pageContext.setAttribute("fractionsList", fractions);

String[] units = {"", "pinch", "tsp", "tbls", "cup", "oz", "lbs"};
pageContext.setAttribute("unitsList", units);

%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="style.css">

<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<!-- <script src="js/add-ingredient.js"></script>   -->
<script type="text/javascript">
        jQuery(function(){
        	jQuery('a.add-ingredient').click(function(event){
            event.preventDefault();

            var newRow = jQuery('<tr style="border: none;">' + 
								'<td style="border: none;">' + 
									'<input style="width:50px; text-align: right;" type="number" name="ingredientAmt1" min="0" max="500" value="0"/>' + 
									'<select style="width:50px; padding:2px" id="amt" name="ingredientAmt2">' + 
										'<c:forEach var="fraction" items="${fractionsList}"><option value="${fraction}">${fraction}</option></c:forEach></select></td>' + 
									'<td style="border: none;">' + 
									'<select style="width:80px"id="unit" name="ingredientUnit">' + 
										'<c:forEach var="unit" items="${unitsList}"><option value="${unit}">${unit}</option></c:forEach></select></td>' + 
								'<td style="border: none;"><input type="text" name="ingredientName" placeholder="Enter Ingredient ${index}" size="25"/></td>' +
								'<td style="border: none;"><a href="#" title="" class="remove-ingredient"><i style="font-size:12; font-weight: normal; text-align: left;">Remove</i></a></td>' +
								'</tr>');
             jQuery('table.ingredient-list').append(newRow);

        	});
        	
        	jQuery('a.remove-ingredient').click(function(event){
        		$(this).parent().parent().remove();
        	});
        });
</script>  		
  
<title>Edit Recipe</title>

<style>
table, th, td {
    border: none;
    border-collapse: collapse;
    margin: 0;
}

select {
    width: 100%;
    padding: 1px 1px;
    border: 1px solid black;
    border-radius: 1px;
}
form {
    margin-block-end: 0em;
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
    	<form method="post" action="RecipeServlet">
            <input type="hidden" name="filterValue" value="${sessionbean.filterValue}">
            <input type="hidden" name="currentPage" value="${sessionbean.currentPage}">
     		<input type="hidden" name="recordsPerPage" value="${sessionbean.recordsPerPage}">
     		<input type="hidden" name="recipeid" value="${sessionbean.currentRecipeId}">
            <a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="List">List Recipes</button></a>
            <a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="RouteToAddScreen">Add Recipe</button></a>
            <a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="Show">Show Recipe</button></a>
        	<a class="active"><button class="active" style="text-align:left;border:none;width:100%" type="submit" name="action" value="Edit">Edit Recipe</button></a>
        </form>
<%--         <a href="RecipeServlet?action=List&recordsPerPage=${sessionBean.recordsPerPage}&currentPage=1&filterValue=${sessionBean.filterValue}">List Recipes</a>
        <a href="addrecipe.jsp">Add Recipe</a>
        <a class="active" href="#editrecipe">Edit Recipe</a> --%>
    </div>
    
    
   	<div class="content">
  	 	<form method="post" action="RecipeServlet" enctype = "multipart/form-data">
   	 		<table style="border: 1px solid black; width: 100%;">
   	 			<tr style="border: none;">
   	 				<th style="width:50%; border: none;"><h2>Recipe Details</h2></th>
   	 				<th style="width:50%; border: none;"><h2>Ingredients    <a href="#" title="" class="add-ingredient"><i style="font-size:12; font-weight: normal;">Add ingredient row</i></a></h2></th>
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
								<td style="width:150px; border: none;">New Image <i style="font-size:12;">(opt)</i></td>
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
								 			<input style="width:50px; text-align: right;" type="number" name="ingredientAmt1" min="0" max="500" value="${ingredient.getIngredientAmtString1()}"/><select style="width:50px; padding:2px" id="amt" name="ingredientAmt2">
								 				<c:forEach var="fraction" items="${fractionsList}">
								 					<option <c:if test="${ingredient.getIngredientAmtString2() eq fraction}">selected="selected"</c:if> value="${fraction}" >${fraction}</option>
								 				</c:forEach>
								 			</select>
										</td>
										<td style="border: none;">
											<select style="width:80px"id="unit" name="ingredientUnit">
								 				<c:forEach var="unit" items="${unitsList}">
								 					<option <c:if test="${ingredient.getIngredientUnit() eq unit}">selected="selected"</c:if> value="${unit}" >${unit}</option>
								 				</c:forEach>
								 			</select>
										</td>	
										<td style="border: none;">
											<input type="text" name="ingredientName" placeholder="Enter Ingredient ${index}" size="25" value="${ingredient.getIngredientName()}"/>
										</td>
										<td style="border: none;">
											<a href="#" title="" class="remove-ingredient"><i style="font-size:12; font-weight: normal;">Remove</i></a>
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
			<input type="hidden" name="recipeid" value="${sessionbean.currentRecipeId}">
			<button type="submit" name="action" value="Update">Save</button>
			<button type="submit" name="action" value="Show">Cancel</button>
		</form>
	</div>

	<div class="footer">
	</div>
</div>


</body>
</html>
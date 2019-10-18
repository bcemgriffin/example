<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page isELIgnored="false" %> 
<%@ page import="beans.RecipeDetailBean"%>
<%@ page import="beans.SessionBean"%>
<%@ page import="beans.IngredientBean"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="beans.MsgBean"%>

<%
SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
pageContext.setAttribute("sessionbean", sessionBean);

RecipeDetailBean recipeobj=(RecipeDetailBean)request.getAttribute("recipeDetailBean");
pageContext.setAttribute("recipe", recipeobj);

ArrayList<IngredientBean> ingredientlistobj = recipeobj.getIngredientlist();
pageContext.setAttribute("ingredientlist", ingredientlistobj);

String node=InetAddress.getLocalHost().getHostName();
pageContext.setAttribute("node", node);

MsgBean msgobj=(MsgBean)request.getAttribute("msgBean");
pageContext.setAttribute("msg", msgobj);

String sessId = session.getId();
pageContext.setAttribute("sessid", sessId);

%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">

<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
<script>
		function printDiv(divName){
			var printContents = document.getElementById(divName).innerHTML;
			var originalContents = document.body.innerHTML;
			document.body.innerHTML = printContents;
			window.print();
			document.body.innerHTML = originalContents;
		}
</script>
    
<title>Recipe Details</title>

<style>
form {
    margin-block-end: 0em;
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
    
    <div class="header">
  	  <c:out value="${msg.message}"/> 
<%--   	  <c:out value="${sessid}"/> --%>
    </div>

    <div class="sidebar">
    	<form method="post" action="RecipeServlet">
            <input type="hidden" name="filterValue" value="${sessionbean.filterValue}">
            <input type="hidden" name="currentPage" value="${sessionbean.currentPage}">
     		<input type="hidden" name="recordsPerPage" value="${sessionbean.recordsPerPage}">
     		<input type="hidden" name="recipeid" value="${sessionbean.currentRecipeId}">
            <a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="List">List Recipes</button></a>
            <a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="RouteToAddScreen">Add Recipe</button></a>
            <a class="active"><button class="active" style="text-align:left;border:none;width:100%" type="submit" name="action" name="action" value="Show">Show Recipe</button></a>
        	<a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="Edit">Edit Recipe</button></a>
        </form>
    </div>
    
    <div class="content">
    	<form method="post" action="RecipeServlet">
	    	<div id='printMe'>
	    		<div class="contentgrid">
     				<div class="content1">
						<table>
	 						<tr>
								<td colspan=2><h2><c:out value="${recipe.getName()}"/></h2></td>
							</tr>
							<tr>
								<td style="border: none; min-width: 150px;">Yields </td>
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
						</table>
						<h3>Ingredients</h3>
						<div class="contentvscroll">
							<table>
								<c:forEach var="ingredient" items="${ingredientlist}">
									<tr style="border: none;">
						 				<td style="border: none; text-align: right;">${ingredient.getIngredientAmtString1()} ${ingredient.getIngredientAmtString2()}</td>
						 				<td style="border: none; min-width: 50px; text-align: left;">${ingredient.getIngredientUnit()}</td>
										<td style="border: none;">${ingredient.getIngredientName()}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
     				<div class="content1p">
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
					 				<td style="border: none;">${ingredient.getIngredientAmtString1()} ${ingredient.getIngredientAmtString2()} ${ingredient.getIngredientUnit()}</td>
									<td style="border: none;">${ingredient.getIngredientName()}</td>
								</tr>
							</c:forEach>
				  		</table>
					</div>
			  		<div class="content2">
						<img src="/MyWebsite/images/${recipe.getPhotoName()}" style="height:320px" alt="Image Not Uploaded - /MyWebsite/images/${recipe.getPhotoName()}" />
			  		</div>

			 		<div class="content3">
			 			<h3>Directions</h3>
		 		 		<div class="contentvscroll">
				 				${recipe.getDirections()}
	 	 				</div>
	 	 				<input type="hidden" name="recipeid" value="${recipe.getId()}">
	 	 				<input type="hidden" name="filterValue" value="${sessionbean.filterValue}">
            			<input type="hidden" name="currentPage" value="${sessionbean.currentPage}">
     					<input type="hidden" name="recordsPerPage" value="${sessionbean.recordsPerPage}">
	 	 				<button style="width:60px" onclick="printDiv('printMe')">Print</button>
	 	 				<button style="width:60px" type="submit" name="action" value="List">Cancel</button>
				 		<button style="width:60px" type="submit" name="action" value="Edit">Edit</button>
				 		<button style="width:60px" type="submit" name="action" value="Delete">Delete</button>
				 	</div>
			 		<div class="content3p">
			 			<h3>Directions</h3>
				 		${recipe.getDirections()}
	 	 			</div>
				</div>
			</div>
		</form>
	</div>
	
	<div class="footer">
        <div class="center"></div>
	</div>
</div>


</body>
</html>
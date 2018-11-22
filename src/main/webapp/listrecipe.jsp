<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ page isELIgnored="false" %> 
<%@ page import="beans.RecipeBean"%>
<%@ page import="beans.RecipeListBean"%>
<%@ page import="beans.SessionBean"%>
<%@ page import="beans.MsgBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%

SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
pageContext.setAttribute("sessionbean", sessionBean);

ArrayList<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
recipelistobj=(ArrayList<RecipeBean>)session.getAttribute("recipeListBean");
pageContext.setAttribute("list", recipelistobj);



MsgBean msgobj = new MsgBean();
msgobj=(MsgBean)request.getAttribute("msgBean");
pageContext.setAttribute("msg", msgobj);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">     
    
<title>Recipe Book - List</title>
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
    
    <div class="header"><c:out value="${msg.message}"/></div>
    
    <div class="search">
       <div class="search-container">
     	<form method="post" action="ReadRecipesServlet">
            <input type="text" name="filterValue" value="${sessionbean.filterValue}">
<!--
            <input type="hidden" name="currentPage" value="1">
     		<input type="hidden" name="recordsPerPage" value="10">
-->
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>    
       </div>    
    </div>
    
    <div class="sidebar">
        <a class="active" href="ReadRecipesServlet?recordsPerPage=${recordsPerPage}&currentPage=1&filterValue=${filterValue}">Recipes</a>
        <a href="#addrecipe">Add Recipe</a>
    </div>
    
    <div class="content">
 <!--   	<c:out value="${msg.message}"/> -->
        <c:if test="${sessionbean.currentPage == 0}">
        	<h1>Welcome to My Recipe Book!</h1>
        </c:if>
        <c:if test="${sessionbean.currentPage != 0}">

  			<form method="post" action="RecipeServlet">
        	<table>
        		<tr>
            	    <th style="width:auto">Recipe Name</th>
            	</tr>
           		<c:forEach var="recipe" items="${sessionbean.recipeListObj}">
     	   		   	<tr>
     	      	    	<td style="width:100%">${recipe.getName()}</td>
     	      	    	
     	      	    	<td style="width:55px"><button type="submit" name="actionAndrecipeid" value="Edit,${recipe.getId()}">Edit</button></td>
     	      	    	
     	      	    	<td style="width:55px"><button type="submit" name="actionAndrecipeid" value="Show,${recipe.getId()}">Show</button></td>
     	      	    
       	     		</tr>
       	    	</c:forEach>
        	</table>
        </form>
        
        </c:if>
	</div>
	
    <div class="footer">
        
      <nav>      
        <ul class="pagination">
        <c:if test="${sessionbean.currentPage > 1}">
            <li class="page-item"><a class="page-link" 
                href="ReadRecipesServlet?recordsPerPage=${sessionbean.recordsPerPage}&currentPage=${sessionbean.currentPage-1}&filterValue=${filterValue}"><<</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${sessionbean.noOfPages}" var="i">
            <c:choose>
                <c:when test="${sessionbean.currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" 
                        href="ReadRecipesServlet?recordsPerPage=${sessionbean.recordsPerPage}&currentPage=${i}&filterValue=${filterValue}"">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${sessionbean.currentPage lt sessionbean.noOfPages}">
            <li class="page-item"><a class="page-link" 
                href="ReadRecipesServlet?recordsPerPage=${sessionbean.recordsPerPage}&currentPage=${sessionbean.currentPage+1}&filterValue=${filterValue}"">>></a>
            </li>
        </c:if>  
	    </ul>	    
      </nav>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>


</body>
</html>
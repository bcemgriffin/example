<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ page isELIgnored="false" %> 
<%@ page import="beans.RecipeBean"%>
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

String sessId = session.getId();
pageContext.setAttribute("sessid", sessId);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
<title>Recipe Book - List</title>

<style>
table, th, tr {
    border: 1px solid black;
    border-collapse: collapse;
    font-size: 17px;
    padding: 5px;
}
th {
    background-color: whitesmoke;
    font-size: 20px;
}
td {
	padding: 0px;
	font-size: 17px;
}
/* button {
	background-color: white;
}
button:hover {
	background-color: whitesmoke;
} */
</style>
    
</head>

<body>
<div class="grid">
    <div class="title">
       	<h1>Online Recipe Book</h1>
    </div>
    
    <div class="header">
     	<c:out value="${msg.message}"/> 
     	<c:out value="${sessid}"/>
    </div>
    
    <div class="search">
       <div class="search-container">
     	<form method="post" action="RecipeServlet">
            <input type="text" name="filterValue" value="${sessionbean.filterValue}">
            <input type="hidden" name="currentPage" value="${sessionbean.currentPage}">
     		<input type="hidden" name="recordsPerPage" value="${sessionbean.recordsPerPage}">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>    
       </div>    
    </div>
    
    <div class="sidebar">
    	<form method="post" action="RecipeServlet">
            <input type="hidden" name="filterValue" value="${sessionbean.filterValue}">
            <input type="hidden" name="currentPage" value="${sessionbean.currentPage}">
     		<input type="hidden" name="recordsPerPage" value="${sessionbean.recordsPerPage}">
            <a class="active"><button class="active" style="text-align:left;border:none;width:100%" type="submit" name="action" value="List">List Recipes</button></a>
            <a><button style="text-align:left;border:none;width:100%" type="submit" name="action" value="RouteToAddScreen">Add Recipe</button></a>
        </form>
<%--         <a class="active" href="RecipeServlet?action=List&recordsPerPage=${sessionBean.recordsPerPage}&currentPage=1&filterValue=${sessionBean.filterValue}">List Recipes</a>
        <a href="addrecipe.jsp">Add Recipe</a> --%>
    </div>
    
    <div class="content">
        <c:if test="${sessionbean.currentPage == 0}">
        	<h2>No Recipes Found</h2>
        </c:if>
        <c:if test="${sessionbean.currentPage != 0}">
  			<form method="post" action="RecipeServlet?action=Show">
        		<table style="width:100%">
           			<c:forEach var="recipe" items="${sessionbean.recipeListObj}">
     	   		   		<tr>
     	      <!-- 	    		<td class="recipe-item"><a href="RecipeServlet?action=Show&recipeid=${recipe.getId()}">${recipe.getName()} </a></td> -->
     	      	    		<td><button style="text-align:left;border:none;width:100%;background-color:white" type="submit" name="recipeid" value="${recipe.getId()}">${recipe.getName()}</button></td>
       	     			</tr>
       	    		</c:forEach>
        		</table>
        	</form>        
        </c:if>
	</div>
	
    <div class="footer">        
    	<nav>      
        <ul class="pagination" style="margin-top: 8px; margin-bottom: 8px;">
        	<c:if test="${sessionbean.currentPage > 1}">
            	<li class="nav-item">
            		<a class="page-link" href="RecipeServlet?action=List&recordsPerPage=${sessionbean.recordsPerPage}&currentPage=${sessionbean.currentPage-1}&filterValue=${filterValue}"><<</a>
            	</li>
        	</c:if>

        	<c:forEach begin="1" end="${sessionbean.noOfPages}" var="i">
 	        	<c:choose>
   	           		<c:when test="${sessionbean.currentPage eq i}">
                		<li class="nav-item active">
                			<a class="page-link">${i}</a>
                		</li>
                	</c:when>
                	<c:otherwise>
                		<li class="nav-item">
                			<a class="page-link" href="RecipeServlet?action=List&recordsPerPage=${sessionbean.recordsPerPage}&currentPage=${i}&filterValue=${filterValue}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

        	<c:if test="${sessionbean.currentPage lt sessionbean.noOfPages}">
            	<li class="nav-item">
            		<a class="page-link" href="RecipeServlet?action=List&recordsPerPage=${sessionbean.recordsPerPage}&currentPage=${sessionbean.currentPage+1}&filterValue=${filterValue}">>></a>
            	</li>
        	</c:if>  
		</ul>	    
	    </nav>
    </div>
</div>

</body>
</html>
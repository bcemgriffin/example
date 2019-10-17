<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="beans.SessionBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%

SessionBean sessionBean = new SessionBean();
session.setAttribute("sessionBean", sessionBean);
sessionBean.setCurrentPage(0);
sessionBean.setRecordsPerPage(10);
sessionBean.setFilterValue("");
sessionBean.setnoOfPages(0);

%>

<!DOCTYPE html>
<html> 
	<c:redirect url="/RecipeServlet"/>   
</html>

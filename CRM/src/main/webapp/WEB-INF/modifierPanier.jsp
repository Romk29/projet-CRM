<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application CRM</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<h1>Modifier un panier</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />

	<form name="ModifPanier" method="POST" action="<c:url value="/ModifierPanier"><c:param name="id" value ="${ panier.id }" /></c:url>">    
            
    <c:import url="/WEB-INF/panier_Form.jsp" /> 
    			          
    </form>
	
</body>
</html>
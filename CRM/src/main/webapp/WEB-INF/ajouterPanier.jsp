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

	<h1>Ajouter un panier</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />
	
	<div class="view">

		<form name="AjoutPanier" method="POST" action="<c:url value="/AjouterPanier"></c:url>">  
	            
	        <fieldset>			
				<legend>Ajouter un panier</legend>
	    		<c:import url="/WEB-INF/panierForm.jsp" /> 
	    	</fieldset>
	    	
	    	<input type="submit" value="Valider">
	        <input type="reset" value="Remettre à zéro">
	    			          
	    </form>
    
    </div>
	
</body>
</html>
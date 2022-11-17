<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter client</title>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />
	
	<h2>Ajouter un client</h2>
	
	<form method="POST" action="<c:url value="/ajouterClient" />">
	
		<c:import url="/WEB-INF/client_form.jsp" />
		
		<input type="submit" value="Valider">
        <input type="reset" value="Remettre à zéro">
	
	</form>

</body>
</html>
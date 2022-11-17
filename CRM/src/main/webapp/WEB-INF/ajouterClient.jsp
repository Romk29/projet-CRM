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
	
	<div class="view">
	
		<form method="POST" action="<c:url value="/ajouterClient" />">
			<fieldset>
			
				<legend>Ajouter un client</legend>
				<c:import url="/WEB-INF/client_form.jsp" />
			
			</fieldset>
			
			<input type="submit" value="Valider">
	        <input type="reset" value="Remettre � z�ro">
		
		</form>
	
	</div>

</body>
</html>
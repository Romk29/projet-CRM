<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application</title>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<h2>Modifier un adresse</h2>

	<c:import url="/WEB-INF/menu.jsp" />
	
	<div class="view">
			
	<form method="POST" action="<c:url value="/ModifierAdresse"><c:param name="id" value="${ adresse.id}" /></c:url>">
	
	<fieldset>
			<legend>Modifier une adresse</legend>
	
		<c:import url="/WEB-INF/adresse_form.jsp" />
		
		<input type="submit" value="Valider">
        <input type="reset" value="Remettre à zéro">
	
	</fieldset>
	
	</form>
	
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier paiement</title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<form method="POST" action="<c:url value="/ModifierPaiement"><c:param name="id" value="${ paiement.id}" /></c:url>">
		
			<fieldset>
				<legend>Modifier un paiement</legend>
		
				<c:import url="/WEB-INF/paiement_form.jsp" />
			</fieldset>
			
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre a zero" />
			
		</form>

	
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter paiements</title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	
		<div class="view">
		
		<form method="POST" action="<c:url value="/AjouterPaiement" />">
		
			<fieldset>
				<legend>Créer un paiement</legend>
				
				<c:import url="/WEB-INF/paiement_form.jsp" />

			</fieldset>
			
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre a zero" />
			
		</form>

	
	</div>

</body>
</html>
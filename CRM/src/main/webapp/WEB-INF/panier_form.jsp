<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<fieldset>
		<legend>Informations panier</legend>
		
			<div class="blocfrom">
				<label for="client">Client :</label> 
					<select name="client" id="client">
						<c:forEach items="${ listeclients }" var="client">
							<option ${ client.id == panier.client.id ? "selected" : "" } value="${ client.id }">
								<c:out value="${ client.nom } ${ client.prenom }" />
							</option>
						</c:forEach>
					</select>
			</div> 

		<div>
			<input class="bas" type="submit" value="Valider" /> 
			<input class="bas" type="reset" value="Réinitialiser" />
		</div>
		
		</fieldset>
	
</body>
</html>
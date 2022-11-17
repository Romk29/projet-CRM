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
		<legend>Informations produit</legend>
		
		<div class="blocfrom">
			<label for="nom">Nom :</label> <input id="nom" name="nom" type="text"
				value="<c:out value="${ produit.nom }"/>" />
				<span class="erreur">${ erreurs['nom'] }</span>
		</div>

		<div class="blocfrom">
			<label for="description">Description :</label> <input id="description" name="description"
				type="text" value="<c:out value="${ produit.description }"/>" />
				<span class="erreur">${ erreurs['description'] }</span>
		</div>

		<div class="blocfrom">
			<label for="prix">Prix :</label> <input
				id="prix" name="prix" type="text"
				value="<c:out value="${ produit.prix }"/>" />
				<span class="erreur">${ erreurs['prix'] }</span>
		</div>

		<div>
			<input class="bas" type="submit" value="Valider" /> 
			<input class="bas" type="reset" value="Réinitialiser" />
		</div>
		
		</fieldset>
	
</body>
</html>
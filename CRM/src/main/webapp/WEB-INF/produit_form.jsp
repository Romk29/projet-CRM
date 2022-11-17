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
			<label for="nomProduit">Nom :</label> <input id="nomProduit" name="nomProduit" type="text"
				value="<c:out value="${ produit.nom }"/>" />
				<span class="erreur">${ erreurs['nom'] }</span>
		</div>

		<div class="blocfrom">
			<label for="descriptionProduit">Description :</label> <input id="descriptionProduit" name="descriptionProduit"
				type="text" value="<c:out value="${ produit.description }"/>" />
				<span class="erreur">${ erreurs['description'] }</span>
		</div>

		<div class="blocfrom">
			<label for="prixProduit">Prix :</label> <input
				id="prixProduit" name="prixProduit" type="text"
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
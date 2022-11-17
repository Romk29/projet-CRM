<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
<title>Paniers</title>
</head>
<body>

	<h1>Paniers enregistrés</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />

	<c:choose>
		<c:when test="${ empty listepaniers }">
			<p>Aucun panier trouvé...</p>
		</c:when>
		<c:otherwise>
			<table>
				<thead>
					<tr>
						<td class="th">Panier n°</td>
						<td class="th">Client</td>
						<td class="th">Actions</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ listepaniers }" var="panier">
						<tr>
							<td><c:out value="${ panier.id }" /></td>
							<td><c:out value="${ panier.client.prenom } ${ panier.client.nom }" /></td>
							<td><a class="actions" href="<c:url value="/DetailsPanier"><c:param name="id" value="${ panier.id }" /></c:url>">Voir /</a> 
								<a class="actions" href="<c:url value="/SupprimerPanier"><c:param name="id" value ="${ panier.id }" /></c:url>">Supprimer /</a> 
								<a class="actions" href="<c:url value="/ModifierPanier"><c:param name="id" value ="${ panier.id }" /></c:url>">Modifier</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>

	<div class="ajouter">
		<a class="lienAjouter" href="<c:url value="/AjouterPanier"></c:url>">Ajouter un nouveau panier</a>
	</div>

</body>
</html>
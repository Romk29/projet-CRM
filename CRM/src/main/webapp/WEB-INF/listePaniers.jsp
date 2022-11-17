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
	
	<div class="view">

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
					<c:forEach items="${ listepaniers }" var="panier" varStatus="infoBoucle">
						<tr class="${ infoBoucle.index % 2 == 0 ? 'pair' : 'impair' }">
							<td><c:out value="${ panier.id }" /></td>
							<td><c:out value="${ panier.client.prenom } ${ panier.client.nom }" /></td>
							<td><a class="actions" href="<c:url value="/DetailsPanier"><c:param name="id" value="${ panier.id }" /></c:url>"><img src="./inc/voir.png" alt="icone voir"></a> 
								<a class="actions" href="<c:url value="/ModifierPanier"><c:param name="id" value ="${ panier.id }" /></c:url>"><img src="./inc/modifier.png" alt="icone modifier"></a> 
								<a class="actions" href="<c:url value="/SupprimerPanier"><c:param name="id" value ="${ panier.id }" /></c:url>"><img src="./inc/supprimer.png" alt="icone supprimer"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>

	<a href="<c:url value="/AjouterPanier" />"><button id="ajout">Ajouter un panier</button></a>
	
	</div>

</body>
</html>
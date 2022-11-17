<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
<title>Application CRM</title>
</head>
<body>

	<h1>Produits enregistr�s</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />

	<c:choose>
		<c:when test="${ empty listeproduits }">
			<p>Aucun produit trouv�...</p>
		</c:when>
		<c:otherwise>
			<table>
				<thead>
					<tr>
						<td class="th">Nom</td>
						<td class="th">Description</td>
						<td class="th">Prix</td>
						<td class="th">Actions</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ listeproduits }" var="produit">
						<tr>
							<td><c:out value="${ produit.nom }" /></td>
							<td><c:out value="${ produit.description }"></c:out></td>
							<td><c:out value="${ produit.prix }"></c:out></td>
							<td><a class="actions" href="<c:url value="/DetailsProduit"><c:param name="id" value="${ produit.id }" /></c:url>">Voir /</a> 
								<a class="actions" href="<c:url value="/SupprimerProduit"><c:param name="id" value ="${ produit.id }" /></c:url>">Supprimer /</a> 
								<a class="actions" href="<c:url value="/ModifierProduit"><c:param name="id" value ="${ produit.id }" /></c:url>">Modifier</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>

	<div class="ajouter">
		<a class="lienAjouter" href="<c:url value="/AjouterProduit"></c:url>">Ajouter un nouveau produit</a>
	</div>

</body>
</html>
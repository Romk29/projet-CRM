<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste Paiements</title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	
		<div class="view">
		
		<a href="<c:url value="/AjouterPaiement" />"><button>Ajouter un paiement</button></a>
		
		<c:choose>
			<c:when test="${ empty paiements }">
				<p>Aucun paiements trouvé...</p>
			</c:when>
			<c:otherwise>	
				<table>
					<thead>
						<tr>
							<td>No Carte</td>
							<td>Code confidentiel</td>
							<td>Banque</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ paiements }" var="paiement" varStatus="infoBoucle">
							<tr class="${ infoBoucle.index % 2 == 0 ? 'pair' : 'impair' }">
								<td><c:out value="${ paiement.noCarte}" /></td>
								<td><c:out value="${ paiement.codeConfidentiel}" /></td>
								<td><c:out value="${ paiement.banque}" /></td>
								<td>
									<a href="<c:url value="/DetailsPaiement"><c:param name="id" value="${ paiement.id}" /></c:url>">Voir</a>
									|
									<a href="<c:url value="/ModifierPaiement"><c:param name="id" value="${ paiement.id}" /></c:url>">Modifier</a>
									|
									<a href="<c:url value="/SupprimerPaiement"><c:param name="id" value="${ paiement.id}" /></c:url>">Supprimer</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
		<span>${ sessionScope.confirmMessage }</span>
				
	</div>

</body>
</html>
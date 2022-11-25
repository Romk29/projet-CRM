<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application CRM</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<h1>Adresses enregistrées</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<c:choose>
			<c:when test="${ empty adresses }">
				<p>Aucun adresse trouvée...</p>
			</c:when>
			<c:otherwise>	
				<table>
					<thead>
						<tr>
							<td class="th">Rue</td>
							<td class="th">Ville</td>
							<td class="th">Pays</td>
							<td class="th">Code Postal</td>
							<td class="th">Actions</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ adresses }" var="adresse" varStatus="infoBoucle">
							<tr class="${ infoBoucle.index % 2 == 0 ? 'pair' : 'impair' }">
								<td><c:out value="${ adresse.rue}" /></td>
								<td><c:out value="${ adresse.ville}" /></td>
								<td><c:out value="${ adresse.pays}" /></td>
								<td><c:out value="${ adresse.codePostal}" /></td>
								<td>
									<a href="<c:url value="/DetailsAdresse"><c:param name="id" value="${ adresse.id}" /></c:url>"><img src="./inc/voir.png" alt="icone voir"></a>
									<a href="<c:url value="/ModifierAdresse"><c:param name="id" value="${ adresse.id}" /></c:url>"><img src="./inc/modifier.png" alt="icone modifier"></a>
									<a href="<c:url value="/SupprimerAdresse"><c:param name="id" value="${ adresse.id}" /></c:url>"><img src="./inc/supprimer.png" alt="icone supprimer"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
		<a href="<c:url value="/AjouterAdresse" />"><button id="ajout">Ajouter une adresse</button></a>
		
		<span>${ sessionScope.confirmMessage }</span>
		
	</div>
</body>
</html>
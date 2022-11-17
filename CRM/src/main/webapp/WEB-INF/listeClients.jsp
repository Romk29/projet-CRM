<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste clients</title>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />
	
	<c:choose>
		<c:when test="${ empty clients }">
			<p>Aucun client trouvé...</p>
		</c:when>
		<c:otherwise>
			<h1>Clients :</h1>
			<table>
				<thead>
					<tr>
						<td>Nom</td>
						<td>Prénom</td>			
						<td>Actions</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ clients }" var="client" varStatus="infoBoucle">
						<tr class="${ infoBoucle.index % 2 == 0 ? 'pair' : 'impair' }">
							<td><c:out value="${ client.nom }"></c:out></td>
							<td><c:out value="${ client.prenom }"></c:out></td>
							<td>
								<a href="<c:url value="/DetailsClient"><c:param name="id" value="${ client.id }" /></c:url>"><img src="./inc/voir.png" alt="icone voir"></a>
								<a href="<c:url value="/ModifierClient"><c:param name="id" value="${ client.id }" /></c:url>"><img src="./inc/modifier.png" alt="icone modifier"></a>
								<a href="<c:url value="/SupprimerClient"><c:param name="id" value="${ client.id }" /></c:url>"><img src="./inc/supprimer.png" alt="icone supprimer"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose><br>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détails client</title>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />
	
	<table>
		<thead>
			<tr>
				<td>Nom</td>
				<td>Prénom</td>			
				<td>Adresse</td>
				<td>Société</td>
				<td>Email</td>
				<td>Téléphone</td>
				<td>Etat</td>
				<td>Genre</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${ client.nom }"></c:out></td>
				<td><c:out value="${ client.prenom }"></c:out></td>
				<td><c:out value="${ client.adresse.rue } ${ client.adresse.ville } ${ client.adresse.codePostal } ${ client.adresse.pays }"></c:out></td>
				<td><c:out value="${ client.nomSociete }"></c:out></td>
				<td><c:out value="${ client.mail }"></c:out></td>
				<td><c:out value="${ client.telephone }"></c:out></td>
				<td><c:out value="${ client.etat }"></c:out></td>
				<td><c:out value="${ client.genre }"></c:out></td>
			</tr>
		</tbody>
	</table>

</body>
</html>
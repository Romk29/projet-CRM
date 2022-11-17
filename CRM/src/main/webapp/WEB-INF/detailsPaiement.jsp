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

	<h1>Détails Paiement</h1>

	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<table>
			<thead>
				<tr>
					<td>Client</td>
					<td>No Carte</td>
					<td>Code confidentiel</td>
					<td>Banque</td>
				</tr>
			</thead>
			<tbody>
				<tr class="pair">
					<td><c:out value="${ paiement.client.prenom} ${ paiement.client.nom}" /></td>
					<td><c:out value="${ paiement.noCarte}" /></td>
					<td><c:out value="${ paiement.codeConfidentiel}" /></td>
					<td><c:out value="${ paiement.banque}" /></td>
				</tr>
			</tbody>
		</table>
	
	</div>
</body>
</html>
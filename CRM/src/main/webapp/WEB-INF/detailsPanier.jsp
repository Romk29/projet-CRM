<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Application CRM</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<h1>Détails Panier</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />
		
		<table>
			<thead>
				<tr>
					<td class="th">Client</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${ panier.client.nom } ${ paiement.client.prenom}" /></td>
					
				</tr>				
			</tbody>
		</table>
	
</body>
</html>
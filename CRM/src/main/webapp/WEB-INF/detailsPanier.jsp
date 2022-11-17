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

	<h1>D�tails Panier</h1>
	
	<c:import url="/WEB-INF/menu.jsp" />
	
	<div class="view">
		
		<table>
			<thead>
				<tr>
					<td class="th">Client</td>
				</tr>
			</thead>
			<tbody>
				<tr class="pair">
					<td><c:out value="${ panier.client.nom } ${ panier.client.prenom}" /></td>
					
				</tr>				
			</tbody>
		</table>
		
	</div>
	
</body>
</html>
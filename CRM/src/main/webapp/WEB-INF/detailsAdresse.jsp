<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>D�tails adresse</title>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />
	
	<table>
		<thead>
			<tr>
				<td>Rue</td>
				<td>Ville</td>			
				<td>Pays</td>
				<td>Code Postal</td>
				
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${ adresse.rue }"></c:out></td>
				<td><c:out value="${ adresse.ville }"></c:out></td>
				<td><c:out value="${ adresse.pays }"></c:out></td>
				<td><c:out value="${ adresse.codePostal }"></c:out></td>
				
			</tr>
		</tbody>
	</table>

</body>
</html>
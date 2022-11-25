<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>

	<nav class="menu">
		<a href="<c:url value="/index.jsp"/>">Accueil</a>
		<a href="<c:url value="/ListeProduits"/>">Produits</a>
		<a href="<c:url value="/ListeClients"/>">Clients</a>
		<a href="<c:url value="/ListePaniers"/>">Paniers</a>
		<a href="<c:url value="/ListePaiements"/>">Paiements</a>
		<a href="<c:url value="/ListeAdresses"/>">Adresses</a>
	</nav>


</body>
</html>
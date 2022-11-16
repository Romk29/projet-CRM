<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<label for="nomAdresse">Nom : </label>
<input id="nomAdresse" name="nomAdresse" type="text" value="<c:out value="${ adresse.nom }" />" />
<!--<span class="erreur">${ erreurs['nomAdresse'] }</span>  -->
<br/>

<label for="prenomAdresse">Prénom : </label>
<input id="prenomAdresse" name="prenomAdresse" type="text" value="<c:out value="${ adresse.prenom }" />" />
<!--  <span class="erreur">${ erreurs['prenomAdresse'] }</span>-->
<br/>

<label for="telephoneAdresse">Téléphone : </label>
<input id="telephoneAdresse" name="telephoneAdresse" type="text" value="<c:out value="${ adresse.telephone }" />" />
<!--  <span class="erreur">${ erreurs['telephoneAdresse'] }</span> -->
<br/>

<label for="emailAdresse">Email : </label>
<input id="emailAdresse" name="emailAdresse" type="text" value="<c:out value="${ adresse.email }" />" />
<!--   <span class="erreur">${ erreurs['emailAdresse'] }</span> -->
<br/>
<!--   
<br/>
<span class="erreur">${ erreurs['adresse'] }</span>
<c:if test="${ not empty erreurs }"> 
	<p class="erreur">Echec de la sauvegarde de l'adresse.</p> 
</c:if>-->
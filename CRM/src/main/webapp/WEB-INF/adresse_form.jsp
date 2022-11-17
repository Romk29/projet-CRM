<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<label for="rueAdresse">Rue : </label>
<input id="rueAdresse" name="rueAdresse" type="text" value="<c:out value="${ adresse.rue }" />" />
<!--<span class="erreur">${ erreurs['nomAdresse'] }</span>  -->
<br/>

<label for="villeAdresse">Ville : </label>
<input id="villeAdresse" name="villeAdresse" type="text" value="<c:out value="${ adresse.ville }" />" />
<!--  <span class="erreur">${ erreurs['prenomAdresse'] }</span>-->
<br/>

<label for="paysAdresse">Pays : </label>
<input id="paysAdresse" name="paysAdresse" type="text" value="<c:out value="${ adresse.pays }" />" />
<!--  <span class="erreur">${ erreurs['telephoneAdresse'] }</span> -->
<br/>

<label for="codePostalAdresse">Code Postal : </label>
<input id="codePostalAdresse" name="codePostalAdresse" type="text" value="<c:out value="${ adresse.codePostal }" />" />
<!--   <span class="erreur">${ erreurs['emailAdresse'] }</span> -->
<br/>
<!--   
<br/>
<span class="erreur">${ erreurs['adresse'] }</span>
<c:if test="${ not empty erreurs }"> 
	<p class="erreur">Echec de la sauvegarde de l'adresse.</p> 
</c:if>-->
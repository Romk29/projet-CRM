<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    	
    	<label for="nomClient">Nom :</label>
        <input type="text" id="nomClient" name="nomClient" value="<c:out value="${ client.nom }" />">
        <span class="erreur">${ erreurs['nomClient'] }</span>
        
        <label for="prenomClient">Pr?nom :</label>
        <input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${ client.prenom }" />">
    	<span class="erreur">${ erreurs['prenomClient'] }</span>
    	
        <label for="adresseClient">Adresse :</label>
	    <select id="adresseClient" name="adresseClient">
	        <option value="">Veuillez s?lectionner une adresse</option>
	        <c:forEach items="${ adresses }" var="adresse">
	           <option value="${ adresse.id }" ${ adresse.id == client.adresse.id ? "selected" : "" }><c:out value="${ adresse.rue }" /> 
	           	<c:out value="${ adresse.ville }" /> <c:out value="${ adresse.codePostal }" /> <c:out value="${ adresse.pays }" /></option>
	        </c:forEach>
	    </select>
	    <span class="erreur">${ erreurs['adresseClient'] }</span>
	                 
        <label for="nomSocieteClient">Soci?t? :</label>
        <input type="text" id="nomSocieteClient" name="nomSocieteClient" value="<c:out value="${ client.nomSociete }" />">
         <span class="erreur">${ erreurs['nomSocieteClient'] }</span>
                
        <label for="mailClient">Email :</label>
        <input type="email" id="mailClient" name="mailClient" value="<c:out value="${ client.mail }" />">
		<span class="erreur">${ erreurs['mailClient'] }</span>

        <label for="telephoneClient">T?l?phone :</label>
        <input type="tel" id="telephoneClient" name="telephoneClient" value="<c:out value="${ client.telephone }" />">
        <span class="erreur">${ erreurs['telephoneClient'] }</span>
        
        <label for="etatClient">Etat :</label>
        <input type="number" id="etatClient" name="etatClient" value="<c:out value="${ client.etat }" />">
        <span class="erreur">${ erreurs['etatClient'] }</span>
        
        <label for="genreClient">Genre :</label>
        <input type="number" id="genreClient" name="genreClient" value="<c:out value="${ client.genre }" />">
        <span class="erreur">${ erreurs['genreClient'] }</span>

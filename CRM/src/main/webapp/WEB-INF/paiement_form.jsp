<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	

<label for="clientPaiement">Client : </label>
<select id="clientPaiement" name="clientPaiement">
	<c:forEach items="${ clients }" var="client">
		<option value="${ client.id}" ${ client.id == paiement.client.id ? "selected" : "" }><c:out value="${ client.prenom}" /> <c:out value="${ client.nom}" /></option>
	</c:forEach>
</select>
<br/>

<label for="noCartePaiement">No Carte :  </label>
<input id="noCartePaiement" name="noCartePaiement" type="number" value="<c:out value="${ paiement.noCarte }" />" />
<br/>

<label for="codeConfidentielPaiement">Code confidentiel :  </label>
<input id="codeConfidentielPaiement" name="codeConfidentielPaiement" type="number" value="<c:out value="${ paiement.codeConfidentiel }" />" />
<br/>


<label for="banquePaiement">Banque : </label>
<input id="banquePaiement" name="banquePaiement" type="text" value="<c:out value="${ paiement.banque }" />" />
<br/>
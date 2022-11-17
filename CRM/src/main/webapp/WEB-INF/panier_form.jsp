<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
		
			<div class="blocfrom">
				<label for="client">Client :</label> 
					<select name="client" id="client">
						<c:forEach items="${ listeclients }" var="client">
							<option ${ client.id == panier.client.id ? "selected" : "" } value="${ client.id }">
								<c:out value="${ client.nom } ${ client.prenom }" />
							</option>
						</c:forEach>
					</select>
			</div>
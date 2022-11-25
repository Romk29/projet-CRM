package fr.m2i.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import services.ServiceException;
import services.ServicePaiement;
import utils.Utils;

/**
 * Servlet implementation class PaiementServlet
 */
@WebServlet("/PaiementServlet")
public class PaiementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			String idPaiement = request.getParameter("idPaiement");
			if(idPaiement != null) {
				Long id = Long.parseLong(idPaiement);
				if(id > 0) {
					responseContent = new ServicePaiement().find(id);
					contentType = "application/json";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idPaiement doit etre strictement superieur à 0.";
				}
			} else {
				responseContent = new ServicePaiement().list();
				contentType = "application/json";
			}
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idLivre n'est pas bon.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			JsonObject data = Utils.getJsonFromBuffer(request);

			new ServicePaiement().create(data);

		} catch(JsonSyntaxException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format des donnees n'est pas bon, veuillez utiliser du JSON.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			JsonObject data = Utils.getJsonFromBuffer(request);

			new ServicePaiement().update(data);

		} catch(JsonSyntaxException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format des donnees n'est pas bon, veuillez utiliser du JSON.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="", contentType = "text";
		int responseStatus = 200;

		try {
			String idPaiement = request.getParameter("idPaiement");
			if(idPaiement != null) {
				Long id = Long.parseLong(idPaiement);
				if(id > 0) {
					new ServicePaiement().delete(id);
					responseContent = "Suppression paiement OK.";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idPaiement doit etre strictement superieur a 0.";
				}
			} else {
				responseStatus = 400;
				responseContent = "Erreur : Le parametre idPaiement est obligatoire.";
			}
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du parametre idPaiement n'est pas bon.";
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}


}

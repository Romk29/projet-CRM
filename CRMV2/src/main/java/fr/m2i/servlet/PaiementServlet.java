package fr.m2i.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.impl.DaoClient;
import fr.m2i.dao.impl.DaoPaiement;
import fr.m2i.model.Client;
import fr.m2i.model.Paiement;
import fr.m2i.model.Panier;
import fr.m2i.model.Produit;
import services.ServiceException;
import services.ServicePaiement;
import utils.Utils;

/**
 * Servlet implementation class PaiementServlet
 */
@WebServlet("/Paiement")
public class PaiementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DaoPaiement daoPaiement;
	private DaoClient daoClient;
	
    public PaiementServlet() {
        super();
        daoPaiement = new DaoPaiement();
        daoClient = new DaoClient();        
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		Gson gson = Utils.getSuperJson();

		try {
			String idPaiement = req.getParameter("id");
			if(idPaiement != null) {
				Paiement paiement = daoPaiement.find(Integer.parseInt(idPaiement));
				response = gson.toJson(paiement);
			} else {
				List<Paiement> paiements = daoPaiement.list();
				response = gson.toJson(paiements);
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Le paiement n'a pas été trouvé.";
			responseStatus = 404;
		}

		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
				
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null, body = "";
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			body = buffer.toString();
			
			/* Pareil que faire ça, StringBuffer est une classe quasi identique à String
			 * 
			 * 			
			  		String body = "";
					String line = null;
					BufferedReader reader = req.getReader();
					while((line = reader.readLine()) != null) {
						body += line;
					}
			 * 
			 */
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations du panier depuis l'objet JSON
			
			int noCarte = data.get("noCarte").getAsInt();
			int codeConfidentiel = data.get("codeConfidentiel").getAsInt();
			String banque = data.get("banque").getAsString();
			int clientId = data.get("client_id").getAsInt();						
			
			
			Client client = daoClient.find(clientId);		
														
			//Création du panier
			Paiement paiement = new Paiement();
			paiement.setNoCarte(noCarte);
			paiement.setCodeConfidentiel(codeConfidentiel);
			paiement.setBanque(banque);
			paiement.setClient(client);

			//Sauvegarde du panier
			daoPaiement.create(paiement);
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
				
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null, body = "";
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			body = buffer.toString();
			
			/* Pareil que faire ça, StringBuffer est une classe quasi identique à String
			 * 
			 * 			
			  		String body = "";
					String line = null;
					BufferedReader reader = req.getReader();
					while((line = reader.readLine()) != null) {
						body += line;
					}
			 * 
			 */
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations du panier depuis l'objet JSON
			int id = data.get("id").getAsInt();
			int noCarte = data.get("noCarte").getAsInt();
			int codeConfidentiel = data.get("codeConfidentiel").getAsInt();
			String banque = data.get("banque").getAsString();
			int clientId = data.get("client_id").getAsInt();						
			
			
			Client client = daoClient.find(clientId);		
														
			//Création du panier
			Paiement paiement = daoPaiement.find(id);
			paiement.setNoCarte(noCarte);
			paiement.setCodeConfidentiel(codeConfidentiel);
			paiement.setBanque(banque);
			paiement.setClient(client);

			//Sauvegarde du panier
			daoPaiement.update(paiement);
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		Gson gson = Utils.getSuperJson();

		try {
			String idPaiement = req.getParameter("id");
			daoPaiement.delete(Integer.parseInt(idPaiement));

		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Le paiement n'a pas été trouvé.";
			responseStatus = 404;
		}

		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}


}

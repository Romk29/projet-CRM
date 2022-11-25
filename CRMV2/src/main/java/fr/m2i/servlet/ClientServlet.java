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
import utils.Utils;
import fr.m2i.dao.impl.DaoAdresse;
import fr.m2i.dao.impl.DaoClient;
import fr.m2i.dao.impl.DaoPaiement;
import fr.m2i.dao.impl.DaoPanier;
import fr.m2i.model.Adresse;
import fr.m2i.model.Client;
import fr.m2i.model.Paiement;
import fr.m2i.model.Panier;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/Client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoClient daoClient;
	private DaoAdresse daoAdresse;
	private DaoPanier daoPanier;
	private DaoPaiement daoPaiement;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        daoClient = new DaoClient();
        daoAdresse = new DaoAdresse();
        daoPanier = new DaoPanier();
        daoPaiement = new DaoPaiement();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		Gson gson = Utils.getSuperJson();
		
		try {
			String idClient = req.getParameter("id");
			if(idClient != null) {
				Client client = daoClient.find(Long.parseLong(idClient));
				response = gson.toJson(client);
			} else {
				List<Client> clients = daoClient.list();
				response = gson.toJson(clients);
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Le client n'a pas été trouvé.";
			responseStatus = 404;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

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
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations du client depuis l'objet JSON
			String nom = data.get("nom").getAsString();
			String prenom = data.get("prenom").getAsString();
			String mail = data.get("mail").getAsString();
			String nomSociete = data.get("nomSociete").getAsString();
			String telephone = data.get("telephone").getAsString();
			int etat = data.get("etat").getAsInt();
			int genre = data.get("genre").getAsInt();
			
			int idAdresse = data.get("idAdresse").getAsInt();
			Adresse adresse = daoAdresse.find(idAdresse);
			
			List<Panier> paniers = new ArrayList<Panier>();
			JsonArray a = data.get("paniers").getAsJsonArray();
			for(JsonElement j : a) {
				int idPanier = j.getAsJsonObject().get("id").getAsInt();
				paniers.add(daoPanier.find(idPanier));
			}
			
			List<Paiement> paiements = new ArrayList<Paiement>();
			JsonArray ar = data.get("paiements").getAsJsonArray();
			for(JsonElement j : ar) {
				int idPaiement = j.getAsJsonObject().get("id").getAsInt();
				paiements.add(daoPaiement.find(idPaiement));
			}
			
			//Création du client
			Client client = new Client();
			client.setNom(nom);
			client.setPrenom(prenom);
			client.setMail(mail);
			client.setNomSociete(nomSociete);
			client.setTelephone(telephone);
			client.setEtat(etat);
			client.setGenre(genre);
			client.setAdresse(adresse);
			client.setPaniers(paniers);
			client.setPaiements(paiements);
			
			//Sauvegarde du client
			daoClient.create(client);
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
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations du client depuis l'objet JSON
			int id = data.get("id").getAsInt();
			String nom = data.get("nom").getAsString();
			String prenom = data.get("prenom").getAsString();
			String mail = data.get("mail").getAsString();
			String nomSociete = data.get("nomSociete").getAsString();
			String telephone = data.get("telephone").getAsString();
			int etat = data.get("etat").getAsInt();
			int genre = data.get("genre").getAsInt();
			
			int idAdresse = data.get("idAdresse").getAsInt();
			Adresse adresse = daoAdresse.find(idAdresse);
			
			List<Panier> paniers = new ArrayList<Panier>();
			JsonArray a = data.get("paniers").getAsJsonArray();
			for(JsonElement j : a) {
				int idPanier = j.getAsJsonObject().get("id").getAsInt();
				paniers.add(daoPanier.find(idPanier));
			}
			
			List<Paiement> paiements = new ArrayList<Paiement>();
			JsonArray ar = data.get("paiements").getAsJsonArray();
			for(JsonElement j : ar) {
				int idPaiement = j.getAsJsonObject().get("id").getAsInt();
				paiements.add(daoPaiement.find(idPaiement));
			}
			
			//Modification du client
			Client client = daoClient.find(id);
			client.setNom(nom);
			client.setPrenom(prenom);
			client.setMail(mail);
			client.setNomSociete(nomSociete);
			client.setTelephone(telephone);
			client.setEtat(etat);
			client.setGenre(genre);
			client.setAdresse(adresse);
			client.setPaniers(paniers);
			client.setPaiements(paiements);
			
			//Sauvegarde du client
			daoClient.update(client);
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
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			String idClient = req.getParameter("id");
			daoClient.delete(Long.parseLong(idClient));
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}
    

}

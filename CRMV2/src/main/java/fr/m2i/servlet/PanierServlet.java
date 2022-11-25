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
import fr.m2i.dao.impl.DaoPanier;
import fr.m2i.dao.impl.DaoProduit;
import fr.m2i.model.Client;
import fr.m2i.model.Panier;
import fr.m2i.model.Produit;
import utils.Utils;


@WebServlet("/Panier")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoPanier daoPanier;
	private DaoClient daoClient;
	private DaoProduit daoProduit;

    public PanierServlet() {
        super();
        daoPanier = new DaoPanier();
        daoClient = new DaoClient();
        daoProduit = new DaoProduit();
    }


	 //Récupération panier
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		Gson gson = Utils.getSuperJson();

		try {
			String idPanier = req.getParameter("id");
			if(idPanier != null) {
				Panier panier = daoPanier.find(Long.parseLong(idPanier));
				response = gson.toJson(panier);
			} else {
				List<Panier> paniers = daoPanier.list();
				response = gson.toJson(paniers);
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Le panier n'a pas été trouvé.";
			responseStatus = 404;
		}

		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}


	 //Création panier
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
			
			Long clientId = data.get("client_id").getAsLong();						
			
			
			List<Produit> produits = new ArrayList<Produit>();
			JsonArray a = data.get("produits").getAsJsonArray();
            for(JsonElement j : a) {
                long id = data.getAsJsonObject().get("id").getAsLong();
                Produit produit = daoProduit.find(id);
                produits.add(produit);
            }
			
			Client client = daoClient.find(clientId);		
														
			//Création du panier
			Panier panier = new Panier();
			panier.setClient(client);
			panier.setProduits(produits);

			//Sauvegarde du panier
			daoPanier.create(panier);
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


	//Modification panier
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
			long id = data.get("id").getAsLong();
			Long clientId = data.get("client_id").getAsLong();						
			
			
			List<Produit> produits = new ArrayList<Produit>();
			JsonArray a = data.get("produits").getAsJsonArray();
            for(JsonElement j : a) {
                long produitId = data.getAsJsonObject().get("produit_id").getAsLong();
                Produit produit = daoProduit.find(produitId);
                produits.add(produit);
            }
			
			Client client = daoClient.find(clientId);
			
			//Création du livre
			Panier panier = daoPanier.find(id);
			
			panier.setClient(client);			
			panier.setProduits(produits);

			//Sauvegarde du livre
			daoPanier.update(panier);
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


	 //Suppression livre
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			String idPanier = req.getParameter("id");
			daoPanier.delete(Long.parseLong(idPanier));
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

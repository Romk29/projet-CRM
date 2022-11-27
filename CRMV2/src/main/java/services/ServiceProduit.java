package services;

import com.google.gson.JsonObject;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.impl.DaoProduit;
import fr.m2i.model.Produit;
import utils.Utils;

public class ServiceProduit {

	private DaoProduit daoProduit;

	public ServiceProduit() {
		super();
		daoProduit = new DaoProduit();
	}

	public String find(int id) throws ServiceException {
		Produit produit;

		try {
			produit = daoProduit.find(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

		return Utils.getSuperJson().toJson(produit);
	}

	public String list() {
		return Utils.getSuperJson().toJson(daoProduit.list());
	}

	public void create(JsonObject data) throws ServiceException {

		try {
			//Récupération des informations de l'produit depuis l'objet JSON
			String nom = Utils.getStringParameter(data, "nom", false, 1, 70);
			String description = Utils.getStringParameter(data, "description", true, 0, 70);
			String prix = Utils.getStringParameter(data, "prix", false, 1, 70);
			
			//Création de l'produit
			Produit produit = new Produit();
			produit.setNom(nom);
			produit.setDescription(description);
			produit.setPrix(Double.parseDouble(prix));
			
			//Sauvegarde de l'produit
			daoProduit.create(produit);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(JsonObject data) throws ServiceException {
		try {
			String id = Utils.getStringParameter(data, "id", false, 0, 50);
			String nom = Utils.getStringParameter(data, "nom", false, 1, 70);
			String description = Utils.getStringParameter(data, "description", true, 0, 70);
			String prix = Utils.getStringParameter(data, "prix", false, 1, 70);
			
			//Création de l'produit
			Produit produit = daoProduit.find(Integer.parseInt(id));
			produit.setNom(nom);
			produit.setDescription(description);
			produit.setPrix(Double.parseDouble(prix));
			
			//Sauvegarde de l'produit
			daoProduit.update(produit);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(int id) throws ServiceException {
		try {
			daoProduit.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}




}
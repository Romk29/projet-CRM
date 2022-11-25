package services;

import com.google.gson.JsonObject;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.impl.DaoAdresse;
import fr.m2i.model.Adresse;
import utils.Utils;

public class ServiceAdresse {

	private DaoAdresse daoAdresse;

	public ServiceAdresse() {
		super();
		daoAdresse = new DaoAdresse();
	}

	public String find(long id) throws ServiceException {
		Adresse adresse;

		try {
			adresse = daoAdresse.find(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

		return Utils.getSuperJson().toJson(adresse);
	}

	public String list() {
		return Utils.getSuperJson().toJson(daoAdresse.list());
	}

	public void create(JsonObject data) throws ServiceException {

		try {
			//Récupération des informations de l'adresse depuis l'objet JSON
			String rue = Utils.getStringParameter(data, "rue", false, 1, 70);
			String ville = Utils.getStringParameter(data, "ville", true, 1, 70);
			String pays = Utils.getStringParameter(data, "pays", false, 1, 70);
			String codePostal= Utils.getStringParameter(data, "codePostal", true, 0, 5);

			//Création de l'adresse
			Adresse adresse = new Adresse();
			adresse.setRue(rue);
			adresse.setVille(ville);
			adresse.setPays(pays);
			adresse.setCodePostal(codePostal);

			//Sauvegarde de l'adresse
			daoAdresse.create(adresse);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(JsonObject data) throws ServiceException {
		try {
			String id = Utils.getStringParameter(data, "rue", false, 0, 50);
			String rue = Utils.getStringParameter(data, "rue", false, 2, 20);
			String ville = Utils.getStringParameter(data, "ville", true, 0, 20);
			String pays = Utils.getStringParameter(data, "pays", false, 0, 10, "^\\d+$");
			String codePostal= Utils.getStringParameter(data, "codePostal", true, 0, 60, "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");

			//Création de l'adresse
			Adresse adresse = daoAdresse.find(Long.parseLong(id));
			adresse.setRue(rue);
			adresse.setVille(ville);
			adresse.setPays(pays);
			adresse.setCodePostal(codePostal);

			//Sauvegarde de l'adresse
			daoAdresse.update(adresse);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			daoAdresse.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}




}
package services;

import com.google.gson.JsonObject;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.impl.DaoClient;
import fr.m2i.dao.impl.DaoPaiement;
import fr.m2i.model.Client;
import fr.m2i.model.Paiement;
import utils.Utils;



public class ServicePaiement {

	private DaoPaiement dao;
	private DaoClient daoClient;

	public ServicePaiement() {
		dao = new DaoPaiement();
		daoClient = new DaoClient();
	}


	public String find(long id) throws ServiceException {
		Paiement paiement;

		try {
			paiement = dao.find(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

		if(paiement == null)
			throw new ServiceException("Le paiement n'existe pas. Id : "+id);

		return Utils.getSuperJson().toJson(paiement);
	}


	public String list() throws ServiceException {
		return Utils.getSuperJson().toJson(dao.list());
	}

	public void create(JsonObject data) throws ServiceException {
		int noCarte = null, int codeConfidentiel = null, String banque = null, idClient = null;
		Client client = null;

		try {
			banque = Utils.getStringParameter(data, "banquePaiement", false, 2, 255);
			idClient = Utils.getStringParameter(data, "idClient", true, 0, 50, "^\\d+$");

			if(idClient != null) {
				client = daoClient.find(Long.parseLong(idClient));
				if(client == null)
					throw new ServiceException("Le livre n'existe pas. Id : "+idClient);

				if(client.getPaiements() != null)
					throw new ServiceException("Le client est déja associé au paiement d'id : "+client.getPaiements().getId());
			}

			Paiement paiement = new Paiement();
			paiement.setBanque(banque);
			paiement.setClient(client);

			dao.create(paiement);

			if(client != null) {
				client.setPaiements(paiement);
				daoClient.update(client);
			}
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void update(JsonObject data) throws ServiceException {
		String id = null, banque = null, idClient = null;
		Client client = null;

		try {
			id = Utils.getStringParameter(data, "idPaiement", false, 0, 50, "^\\d+$");
			banque = Utils.getStringParameter(data, "banquePaiement", false, 2, 255);
			idClient = Utils.getStringParameter(data, "idClient", true, 0, 50, "^\\d+$");

			Paiement paiement = dao.find(Long.parseLong(id));
			if(paiement == null)
				throw new ServiceException("Le paiement n'existe pas. Id : "+id);


			if(idClient != null) {
				client = daoClient.find(Long.parseLong(idClient));
				if(client == null)
					throw new ServiceException("Le client n'existe pas. Id : "+idClient);

				if(client.getPaiements() != null && client.getPaiements().getId() != Long.parseLong(id))
					throw new ServiceException("Le client est déja associé au paiement d'id : "+client.getPaiements().getId());
			} else {
				if(paiement.getClient() != null) {
					Client clientOld = paiement.getClient();
					clientOld.setPaiements(null);
					daoClient.update(clientOld);
				}
			}

			paiement.setBanque(banque);
			paiement.setClient(client);

			dao.update(paiement);


			if(client != null) {
				client.setPaiements(paiement);
				daoClient.update(client);
			}

		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramétre idPaiement n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Le paiement n'existe pas. Id : "+id);
		}
	}

}

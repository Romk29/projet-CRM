package fr.m2i.launcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.m2i.dao.AdresseDao;
import fr.m2i.dao.ClientDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.dao.PanierDao;
import fr.m2i.model.Adresse;
import fr.m2i.model.Client;
import fr.m2i.model.Panier;


public class Launcher {

	public static void main(String[] args) {
		

		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;
	
		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		List<Client> listClients;
		
		List<Panier> listPaniers;
		
		PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
		
		
		try {
			Panier panier1;
			panier1 = panierDao.trouver(31);
			System.out.println(panier1);
		
			Client client1;
			client1= clientDao.trouver(13);
			System.out.println(client1);
						
			panier1.setClient(client1);
			
			panierDao.miseAJour(panier1);
			
			panier1 = panierDao.trouver(31);
			System.out.println(panier1);
			
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	} 
					   	
	
}

package fr.m2i.dao;

import java.util.List;

import fr.m2i.model.Client;


public interface ClientDao {

	void          creer( Client client ) throws DaoException;

	Client       trouver( int id ) throws DaoException;

    List<Client> lister() throws DaoException;

    void          supprimer( int id ) throws DaoException;
    
	void          miseAJour( Client client ) throws DaoException;

	
}

package fr.m2i.dao;

import java.util.List;

import fr.m2i.model.Panier;


public interface PanierDao {

	void          creer( Panier panier ) throws DaoException;

	Panier       trouver( int id ) throws DaoException;

    List<Panier> lister() throws DaoException;

    void          supprimer( int id ) throws DaoException;
    
	void          miseAJour( Panier panier ) throws DaoException;

	
}
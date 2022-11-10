package fr.m2i.dao;

import java.util.List;

import fr.m2i.model.Adresse;


public interface AdresseDao {

	void          creer( Adresse adresse ) throws DaoException;

	Adresse       trouver( int id ) throws DaoException;

    List<Adresse> lister() throws DaoException;

    void          supprimer( int id ) throws DaoException;
    
	void          miseAJour( Adresse adresse ) throws DaoException;

	
}

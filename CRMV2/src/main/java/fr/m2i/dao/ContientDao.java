package fr.m2i.dao;

import java.util.List;

import fr.m2i.model.Contient;

public interface ContientDao {
	
	void          creer( Contient contient ) throws DaoException;

	Contient       trouver( int id ) throws DaoException;

    List<Contient> lister() throws DaoException;

    void          supprimer( int id ) throws DaoException;
    
	void          miseAJour( Contient contient ) throws DaoException;

}

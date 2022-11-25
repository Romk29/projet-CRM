package fr.m2i.dao;

import java.util.List;

import fr.m2i.model.Paiement;

public interface PaiementDao {
	
	void          creer( Paiement paiement ) throws DaoException;

	Paiement       trouver( int id ) throws DaoException;

    List<Paiement> lister() throws DaoException;

    void          supprimer( int id ) throws DaoException;
    
	void          miseAJour( Paiement paiement ) throws DaoException;

}

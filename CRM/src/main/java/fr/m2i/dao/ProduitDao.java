package fr.m2i.dao;

import java.util.List;

import fr.m2i.model.Produit;

public interface ProduitDao {
	
	void          creer( Produit produit ) throws DaoException;

	Produit       trouver( int id ) throws DaoException;

    List<Produit> lister() throws DaoException;

    void          supprimer( int id ) throws DaoException;
    
	void          miseAJour( Produit produit ) throws DaoException;

}

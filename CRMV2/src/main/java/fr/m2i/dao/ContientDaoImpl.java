package fr.m2i.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.m2i.model.Contient;


public class ContientDaoImpl implements ContientDao {

	private static final String SQL_INSERT       = "INSERT INTO contient(id_produit,id_panier) VALUES(?,?)";
	private static final String SQL_SELECT       = "SELECT id_produit,id_panier FROM contient";
    private static final String SQL_SELECT_BY_ID = "SELECT id_produit,id_panier FROM contient WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM contient WHERE id = ? ";
	
	private static final String SQL_UPDATE = "UPDATE contient SET id_produit=?, id_panier=? WHERE id = ?";
	
	private DaoFactory factory;
	
	protected ContientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void creer(Contient contient) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setInt( 1, contient.getProduit().getId() );
			pst.setInt( 1, contient.getPanier().getId() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Contient (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
            	contient.setId( rsKeys.getInt( 1 ) );
            } else {
                throw new DaoException( "Echec cr�ation Contient (ID non retourn�)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Contient",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public Contient trouver(int id) throws DaoException {
		Contient            contient=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  contient = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Contient");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Contient", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return contient;
	}

	@Override
	public List<Contient> lister() throws DaoException {
		List<Contient> listeContient = new ArrayList<Contient>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeContient.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Contient", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeContient;
	}

	@Override
	public void supprimer(int id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_DELETE_BY_ID );
			  pst.setLong(1, id);
			  int statut = pst.executeUpdate();
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression Contient("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Contient", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void miseAJour(Contient contient) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setInt( 1, contient.getProduit().getId() );
			pst.setInt( 1, contient.getPanier().getId() );
			pst.setLong( 5, contient.getId() );

			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec modification Produit" );
            }
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec m Produit",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}


 private static Contient map( ResultSet resultSet ) throws SQLException {
	 Contient l = new Contient();
     l.setId( resultSet.getInt( "id" ) );
     
     ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
     try {
			l.setProduit(produitDao.trouver(resultSet.getInt( "id_produit" )));
		} catch (DaoException e) {
			e.printStackTrace();
		}
     PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
     try {
			l.setPanier(panierDao.trouver(resultSet.getInt( "id_panier" )));
		} catch (DaoException e) {
			e.printStackTrace();
		}
     
     
     return l;
    }


}
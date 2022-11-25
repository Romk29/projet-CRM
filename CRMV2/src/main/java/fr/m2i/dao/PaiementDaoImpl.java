package fr.m2i.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.m2i.model.Paiement;



public class PaiementDaoImpl implements PaiementDao {
	
	private static final String SQL_INSERT       = "INSERT INTO paiement(no_carte,code_confidentiel,banque,id_client) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT * FROM paiement";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM paiement WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM paiement WHERE id = ? ";
	
	private static final String SQL_UPDATE = "UPDATE paiement SET id_client=?, no_Carte=?, code_Confidentiel=?, banque=? WHERE id = ?";
	
	private DaoFactory factory;
	
	protected PaiementDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Paiement paiement) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setInt( 1, paiement.getNoCarte() );
			pst.setInt( 2, paiement.getCodeConfidentiel() );
			pst.setString( 3, paiement.getBanque() );
			pst.setInt( 4, paiement.getClient().getId() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec creation Paiement (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                paiement.setId( rsKeys.getInt( 1 ) );
            } else {
                throw new DaoException( "Echec creation Paiement (ID non retourne)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec creation Paiement",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public Paiement trouver(int id) throws DaoException {
		Paiement            paiement=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setInt(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  paiement = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Paiement");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Paiement", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return paiement;
	}


	@Override
	public List<Paiement> lister() throws DaoException {
		List<Paiement> listePaiements = new ArrayList<Paiement>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
	    	  	listePaiements.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Paiement", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listePaiements;
	}

	@Override
	public void supprimer(int id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_DELETE_BY_ID );
			  pst.setInt(1, id);
			  int statut = pst.executeUpdate();
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression Paiement("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Paiement", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void miseAJour(Paiement paiement) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );

			pst.setInt( 1, paiement.getClient().getId() );
			pst.setInt( 2, paiement.getNoCarte());
			pst.setInt( 3, paiement.getCodeConfidentiel() );
			pst.setString( 4, paiement.getBanque());
			pst.setInt( 5, paiement.getId() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec mise à jour Paiement" );
            }
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec mise à jour Paiement",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}
	
	private Paiement map(ResultSet resultSet) throws SQLException {
        Paiement p = new Paiement();
		p.setId( resultSet.getInt( "id" ) );
        
        ClientDao clientDao = DaoFactory.getInstance().getClientDao();
        try {
			p.setClient(clientDao.trouver(resultSet.getInt( "id_client" )));
		} catch (DaoException e) {
			e.printStackTrace();
		}
        
        p.setNoCarte(resultSet.getInt( "no_carte" ));
        p.setCodeConfidentiel(resultSet.getInt( "code_confidentiel" ));
        p.setBanque(resultSet.getString( "banque" ));
  
		return p;
 
    }

}

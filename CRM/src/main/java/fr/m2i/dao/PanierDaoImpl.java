package fr.m2i.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.m2i.model.Panier;


public class PanierDaoImpl implements PanierDao {

	private static final String SQL_INSERT       = "INSERT INTO panier(id_client) VALUES(?)";
	private static final String SQL_SELECT       = "SELECT * FROM panier";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM panier WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM panier WHERE id = ? ";
	private static final String SQL_UPDATE       = "UPDATE panier SET id_client = ? WHERE id = ?";
	
	private DaoFactory factory;
	
	public PanierDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}	
	
	@Override
	public void creer(Panier panier) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setInt( 1, panier.getClient().getId() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Panier (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                panier.setId( rsKeys.getInt( 1 ) );
            } else {
                throw new DaoException( "Echec cr�ation Panier (ID non retourn�)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Panier",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}	
	}

	@Override
	public Panier trouver(int id) throws DaoException {
		Connection con=null;
		Panier panier = null;
		try {
			con = factory.getConnection();
			
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				panier = map(rs);
			}
			rs.close();
			ps.close();

		} catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Panier",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return panier;
		
	}

	@Override
	public List<Panier> lister() throws DaoException {
		
		List<Panier> listePaniers = new ArrayList<Panier>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement ps = con.prepareStatement(SQL_SELECT);
		      ResultSet rs = ps.executeQuery();
		      while ( rs.next() ) {
		    	  listePaniers.add( map(rs) );
		      }
		      rs.close();
		      ps.close();
		      
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Panier", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
		return null;
	}

	@Override
	public void supprimer(int id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement ps = con.prepareStatement(SQL_DELETE_BY_ID);
			  ps.setInt(1, id);
			  
			  int statut = ps.executeUpdate();
			  
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression Panier("+id+")");
			  }
		      ps.close();
		      
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Panier", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	private static Panier map(ResultSet resultSet) throws SQLException {
		Panier p = new Panier();
        p.setId( resultSet.getInt("id"));
        ClientDao clientDao = DaoFactory.getInstance().getClientDao();
        try {
			p.setClient(clientDao.trouver(resultSet.getInt("id_client")));
		} catch (DaoException e) {
			e.printStackTrace();
		}        
        return p;
    }
	
	
	@Override
	public void miseAJour(Panier panier) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_UPDATE); 
			ps.setInt(1, panier.getClient().getId());
			ps.setInt(2, panier.getId());
			
			int statut = ps.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Panier (aucun ajout)" );
            }
            
			ps.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation LPanier",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

}

package fr.m2i.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.m2i.model.Produit;

public class ProduitDaoImpl implements ProduitDao {

	private static final String SQL_INSERT       = "INSERT INTO produit(nom, description, prix) VALUES(?, ?, ?)";
	private static final String SQL_SELECT       = "SELECT * FROM produit";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM produit WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM produit WHERE id = ? ";
	private static final String SQL_UPDATE       = "UPDATE produit SET nom = ?, description = ?, prix = ? WHERE id = ?";
	
	private DaoFactory factory;
	
	public ProduitDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void creer(Produit produit) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setString( 1, produit.getNom() );
			pst.setString( 2, produit.getDescription() );
			pst.setDouble( 3, produit.getPrix() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Produit (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                produit.setId( rsKeys.getInt( 1 ) );
            } else {
                throw new DaoException( "Echec cr�ation Produit (ID non retourn�)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Produit",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	@Override
	public Produit trouver(int id) throws DaoException {
		Connection con=null;
		Produit produit = null;
		try {
			con = factory.getConnection();
			
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				produit = map(rs);
			}
			rs.close();
			ps.close();

		} catch(SQLException ex) {
	    	throw new DaoException("Echec recherche Produit",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
		return produit;
				
	}

	@Override
	public List<Produit> lister() throws DaoException {
		
		List<Produit> listeProduits = new ArrayList<Produit>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement ps = con.prepareStatement(SQL_SELECT);
		      ResultSet rs = ps.executeQuery();
		      while ( rs.next() ) {
		    	  listeProduits.add( map(rs) );
		      }
		      rs.close();
		      ps.close();
		      
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Produit", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeProduits;
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
				  throw new DaoException("Erreur de suppression Produit("+id+")");
			  }
		      ps.close();
		      
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Produit", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	private static Produit map(ResultSet resultSet) throws SQLException {
		Produit pr = new Produit();
        pr.setId( resultSet.getInt("id"));
        pr.setNom( resultSet.getString( "nom" ) );
        pr.setDescription( resultSet.getString( "description" ) );
        pr.setPrix( resultSet.getDouble( "prix" ) );
        
        return pr;
    }
	
	
	@Override
	public void miseAJour(Produit produit) throws DaoException {

		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setString( 1, produit.getNom() );
			pst.setString( 2, produit.getDescription() );
			pst.setDouble( 3, produit.getPrix() );
			pst.setInt( 4, produit.getId() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Produit (aucun ajout)" );
            }
            
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Produit",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}				

}

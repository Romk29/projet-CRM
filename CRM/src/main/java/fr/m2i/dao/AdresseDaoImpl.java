package fr.m2i.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.m2i.model.Adresse;



public class AdresseDaoImpl implements AdresseDao {

	private static final String SQL_INSERT       = "INSERT INTO adresse(rue,ville,pays,codePostal) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id,rue,ville,pays,codePostal FROM adresse";
    private static final String SQL_SELECT_BY_ID = "SELECT id,rue,ville,pays,codePostal FROM adresse WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM adresse WHERE id = ? ";
	
	private static final String SQL_UPDATE = "UPDATE adresse SET rue=?, ville=?, pays=?, codePostal=? WHERE id = ?";
	
	private DaoFactory factory;
	
	protected AdresseDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
		
	@Override
	public void creer(Adresse adresse) throws DaoException {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setString( 1, adresse.getRue() );
			pst.setString( 2, adresse.getVille() );
			pst.setString( 3, adresse.getPays() );
			pst.setInt( 4, adresse.getCodePostal() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Adresse (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
            	adresse.setId( rsKeys.getInt( 1 ) );
            } else {
                throw new DaoException( "Echec cr�ation Adresse (ID non retourn�)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Adresse",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	@Override
	public Adresse trouver(int id) throws DaoException {
		Adresse            adresse=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  adresse = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Adresse");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Adresse", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return adresse;
	}

	@Override
	public List<Adresse> lister() throws DaoException {
		List<Adresse> listeAdresse = new ArrayList<Adresse>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeAdresse.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Adresse", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeAdresse;
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
				  throw new DaoException("Erreur de suppression Adresse("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Adresse", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}

	}

	@Override
	public void miseAJour(Adresse adresse) throws DaoException {
		
			Connection con=null;
			try {
				con = factory.getConnection();
				
				PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
				pst.setString( 1, adresse.getRue() );
				pst.setString( 2, adresse.getVille() );
				pst.setString( 3, adresse.getPays() );
				pst.setInt( 4, adresse.getCodePostal() );
				pst.setLong( 5, adresse.getId() );

				int statut = pst.executeUpdate();

	            if ( statut == 0 ) {
	                throw new DaoException( "Echec modification Auteur" );
	            }
				pst.close();
				
		    } catch(SQLException ex) {
		    	throw new DaoException("Echec m Auteur",ex);
		    } finally {
		    	factory.releaseConnection(con);
			}
		}

	
	 private static Adresse map( ResultSet resultSet ) throws SQLException {
		 Adresse a = new Adresse();
	        a.setId( resultSet.getInt( "id" ) );
	        a.setRue( resultSet.getString( "nom" ) );
	        a.setVille( resultSet.getString( "ville" ) );
	        a.setPays( resultSet.getString( "pays" ) );
	        a.setCodePostal( resultSet.getInt( "codePostal" ) );
	        return a;
	    }
	

}

package fr.m2i.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.m2i.model.Client;

public class ClientDaoImpl implements ClientDao {
	
	private static final String SQL_INSERT       = "INSERT INTO client(id_adresse, nom_societe, mail, nom, prenom, telephone, etat, genre) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id, id_adresse, nom_societe, mail, nom, prenom, telephone, etat, genre FROM client";
    private static final String SQL_SELECT_BY_ID = "SELECT id, id_adresse, nom_societe, mail, nom, prenom, telephone, etat, genre FROM client WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM client WHERE id = ? ";
	private static final String SQL_UPDATE       = "UPDATE client SET id_adresse=?, nom_societe=?, mail=?, nom=?, prenom=?, telephone=?, etat=?, genre=? WHERE id = ?";
	
	private DaoFactory factory;
	
	public ClientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Client client) throws DaoException {
		
		Connection con=null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, client.getAdresse().getId());
			pst.setString(2, client.getNomSociete());
			pst.setString(3, client.getMail());
			pst.setString(4, client.getNom());
			pst.setString(5, client.getPrenom());
			pst.setString(6, client.getTelephone());
			pst.setInt(7, client.getEtat());
			pst.setInt(8, client.getGenre());
			
			int statut = pst.executeUpdate();
            if (statut == 0) {
                throw new DaoException("Echec création client (aucun ajout)");
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if (rsKeys.next()) {
                client.setId(rsKeys.getInt( 1 ));
            } else {
                throw new DaoException("Echec création client (ID non retourné)");
            }
            rsKeys.close();
			pst.close();
			
		} catch(SQLException ex) {
	    	throw new DaoException("Echec création client",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public Client trouver(int id) throws DaoException {
		
		Client client = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				client = map(rs);
			}
			rs.close();
			pst.close();		
		} catch(SQLException e) {
			throw new DaoException("Erreur de recherche BDD client", e);
		} finally {
			factory.releaseConnection(con);
		}
		
		return client;
	}
	
	private static Client map(ResultSet resultSet) throws SQLException {
		
		Client client = new Client();
		AdresseDao adresseDao = DaoFactory.getInstance().getAdresseDao();
		
		client.setId(resultSet.getInt("id"));
		try {
			client.setAdresse(adresseDao.trouver(resultSet.getInt("id_adresse")));
		} catch (DaoException e) {
			e.printStackTrace();
		}	
		client.setNomSociete(resultSet.getString("nom_societe"));
		client.setMail(resultSet.getString("mail"));
		client.setNom(resultSet.getString("nom"));
		client.setPrenom(resultSet.getString("prenom"));
		client.setTelephone(resultSet.getString("telephone"));
		client.setEtat(resultSet.getInt("etat"));
		client.setGenre(resultSet.getInt("genre"));
		
		return client;
	}	

	@Override
	public List<Client> lister() throws DaoException {
		
		List<Client> listeClients = new ArrayList<Client>();
		Connection con = null;
		
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				listeClients.add(map(rs));
			}
			rs.close();
			pst.close();
			
		} catch(SQLException e) {
			throw new DaoException("Erreur de lecture BDD client", e);
		} finally {
			factory.releaseConnection(con);
		}
		
		return listeClients;
	}

	@Override
	public void supprimer(int id) throws DaoException {
		
		Connection con = null;
		
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_DELETE_BY_ID);
			pst.setInt(1, id);
			
			int statut = pst.executeUpdate();
			if(statut == 0) {
				throw new DaoException("Erreur de suppression client ("+ id +")");
			}
			pst.close();
			
		} catch(SQLException e) {
			throw new DaoException("Erreur de suppression BDD client", e);
		} finally {
			factory.releaseConnection(con);
		}
		
	}

	@Override
	public void miseAJour(Client client) throws DaoException {
		
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
			pst.setInt(1, client.getAdresse().getId());
			pst.setString(2, client.getNomSociete());
			pst.setString(3, client.getMail());
			pst.setString(4, client.getNom());
			pst.setString(5, client.getPrenom());
			pst.setString(6, client.getTelephone());
			pst.setInt(7, client.getEtat());
			pst.setInt(8, client.getGenre());
			pst.setInt(9, client.getId());
			
			int statut = pst.executeUpdate();
			if(statut == 0) {
				throw new DaoException("Echec modification client (aucun ajout)");
			}
			pst.close();
			
		} catch(SQLException e) {
			throw new DaoException("Echec modification client", e);
		} finally {
			factory.releaseConnection(con);
		}
		
	}

}
